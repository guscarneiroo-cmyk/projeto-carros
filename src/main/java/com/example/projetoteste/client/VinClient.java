package com.example.projetoteste.client;

import com.example.projetoteste.domain.dto.VinResponse;
import com.example.projetoteste.exception.ExternalBadRequestException;
import com.example.projetoteste.exception.ExternalMappingException;
import com.example.projetoteste.exception.ExternalUnavailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class VinClient {

    private static final String PROVIDER = "api-ninjas";
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    public VinClient(
            RestTemplate restTemplate,
            @Value("${api-ninjas.base-url}") String baseUrl,
            @Value("${api-ninjas.api-key}") String apiKey
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public VinResponse buscaVin(String vin) {
        String url = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/vinlookup")
                .queryParam("vin", vin)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<VinResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    VinResponse.class
            );

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                // 2xx sem body é “estranho” => mapping/contrato inesperado
                throw new ExternalMappingException(PROVIDER, "Resposta externa sem body (2xx) para vinlookup.", null);
            }

            return response.getBody();

        } catch (HttpStatusCodeException ex) {
            // Aqui cai quando o provedor responde 4xx/5xx.
            HttpStatus status = (HttpStatus) ex.getStatusCode();
            String body = ex.getResponseBodyAsString();

            if (status.is4xxClientError()) {
                // DoD: 4xx externo -> 502
                throw new ExternalBadRequestException(PROVIDER, status, body);
            }

            // DoD: 5xx externo -> 503
            throw new ExternalUnavailableException(PROVIDER,
                    "Provedor externo indisponível: " + PROVIDER + " (status=" + status + ")", ex);

        } catch (ResourceAccessException ex) {
            // Timeouts / conexão recusada / DNS etc
            throw new ExternalUnavailableException(PROVIDER,
                    "Timeout/erro de rede ao chamar provedor externo: " + PROVIDER, ex);

        } catch (RestClientException ex) {
            // Conversão/mapping pode aparecer aqui via causa
            if (isMappingIssue(ex)) {
                // DoD: parse inesperado -> 502
                throw new ExternalMappingException(PROVIDER,
                        "Falha ao interpretar resposta do provedor externo: " + PROVIDER, ex);
            }

            throw new ExternalUnavailableException(PROVIDER,
                    "Erro inesperado ao chamar provedor externo: " + PROVIDER, ex);
        }
    }

    private boolean isMappingIssue(RestClientException ex) {
        Throwable cause = ex;
        while (cause != null) {
            if (cause instanceof HttpMessageConversionException) return true;
            cause = cause.getCause();
        }
        return false;
    }
}
