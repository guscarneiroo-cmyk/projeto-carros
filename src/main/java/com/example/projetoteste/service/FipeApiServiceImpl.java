package com.example.projetoteste.service;


import com.example.projetoteste.domain.fipeapi.FipeAPIresponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FipeApiServiceImpl implements FipeApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public FipeAPIresponse getDetails(
            String vehicleType,
            String brandId,
            String modelId,
            String yearId
    ) {
        String url = UriComponentsBuilder
                .fromUriString("https://fipe.parallelum.com.br/api/v2/{vehicleType}/brands/{brandId}/models/{modelId}/years/{yearId}")
                .buildAndExpand(vehicleType, brandId, modelId, yearId)
                .toUriString();

        return restTemplate.getForObject(url, FipeAPIresponse.class);
    }
}