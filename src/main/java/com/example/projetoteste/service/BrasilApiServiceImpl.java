package com.example.projetoteste.service;

import com.example.projetoteste.domain.brasilapi.BrasilApiResponse;
import com.example.projetoteste.domain.dto.BrasilAPIveiculoDTO;
import com.example.projetoteste.domain.dto.VeiculoFaixaValorDTO;
import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Service
public class BrasilApiServiceImpl implements BrasilApiService {


    private final RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(BrasilApiServiceImpl.class);

    @Autowired
    public BrasilApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public VeiculoMenorValorDTO buscarMenorValor(String codigoFipe) {
        String url = "https://brasilapi.com.br/api/fipe/preco/v1/" + codigoFipe;

        List<BrasilApiResponse.BrasilVeiculo> veiculos = getApiBrasilVeiculos(url);

        BrasilApiResponse.BrasilVeiculo menor = veiculos.stream()
                .min(Comparator.comparing(this::parseValor))
                .orElseThrow(() ->
                        new IllegalStateException("Nenhum veículo encontrado para o código " + codigoFipe)
                );

    BigDecimal valorNumerico = parseValor(menor);

    log.info("Menor valor encontrado: {} | {} {}", valorNumerico, menor.getMarca(), menor.getModelo());

    return new VeiculoMenorValorDTO(
            menor.getMarca(),
            menor.getModelo(),
            menor.getAnoModelo(),
            menor.getCombustivel(),
            menor.getCodigoFipe(),
            valorNumerico,
            menor.getMesReferencia(),
            menor.getTipoVeiculo()
    );
}

    private List<BrasilApiResponse.BrasilVeiculo> getApiBrasilVeiculos(String url) {
        BrasilApiResponse.BrasilVeiculo[] response =
                restTemplate.getForObject(url, BrasilApiResponse.BrasilVeiculo[].class);

        if (response == null) {
            return List.of();
        }

        return Arrays.asList(response);
    }

    private BigDecimal parseValor(BrasilApiResponse.BrasilVeiculo v) {
        if (v == null || v.getValor() == null || v.getValor().isBlank()) {
            return new BigDecimal("9999999999");
        }

        String raw = v.getValor()
                .replace("R$", "")
                .replace("\u00A0", " ")
                .trim()
                .replace(".", "")
                .replace(",", ".");

        raw = raw.replaceAll("[^0-9.]", "");

        if (raw.isBlank()) {
            return new BigDecimal("9999999999");
        }

        return new BigDecimal(raw);
    }

    @Override
    public VeiculoFaixaValorDTO buscarFaixaValores(String codigoFipe) {
        String url = "https://brasilapi.com.br/api/fipe/preco/v1/" + codigoFipe;

        List<BrasilApiResponse.BrasilVeiculo> veiculos = getApiBrasilVeiculos(url);

        if (veiculos.isEmpty()) {
            throw new IllegalStateException("Nenhum veículo encontrado para o código " + codigoFipe);
        }

        BigDecimal menor = null;
        BigDecimal maior = null;
        BigDecimal soma = BigDecimal.ZERO;
        int qtd = 0;

        for (BrasilApiResponse.BrasilVeiculo v : veiculos) {
            BigDecimal valor = parseValor(v);

            if (valor.compareTo(new BigDecimal("9999999999")) >= 0) {
                continue;
            }

            if (menor == null || valor.compareTo(menor) < 0) menor = valor;
            if (maior == null || valor.compareTo(maior) > 0) maior = valor;

            soma = soma.add(valor);
            qtd++;
        }

        if (qtd == 0) {
            throw new IllegalStateException("A BrasilAPI retornou valores inválidos para o código " + codigoFipe);
        }

        BigDecimal media = soma.divide(
                BigDecimal.valueOf(qtd),
                2
        );

        return new VeiculoFaixaValorDTO(menor, maior, media, qtd);
    }

}





