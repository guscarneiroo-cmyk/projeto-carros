package com.example.projetoteste.service;


import com.example.projetoteste.domain.fipeapi.FipeAPIresponse;
import com.example.projetoteste.domain.fipeapi.FipeYearOptionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Optional;

@Service
public class FipeParallelumService {

    private final RestTemplate restTemplate = new RestTemplate();

    public FipeYearOptionDTO[] listYears(String vehicleType, String fipeCode) {
        String url = UriComponentsBuilder
                .fromUriString("https://fipe.parallelum.com.br/api/v2/{vehicleType}/{fipeCode}/years")
                .buildAndExpand(vehicleType, fipeCode)
                .toUriString();

        return restTemplate.getForObject(url, FipeYearOptionDTO[].class);
    }

    public FipeAPIresponse getDetailByYear(String vehicleType, String fipeCode, String yearId) {
        String url = UriComponentsBuilder
                .fromUriString("https://fipe.parallelum.com.br/api/v2/{vehicleType}/{fipeCode}/years/{yearId}")
                .buildAndExpand(vehicleType, fipeCode, yearId)
                .toUriString();

        return restTemplate.getForObject(url, FipeAPIresponse.class);
    }

    public String resolveYearId(FipeYearOptionDTO[] years, Integer anoModelo, String combustivel) {
        if (years == null || years.length == 0) return null;

        // 1) tenta bater "ano + combustivel" no name (ex: "2014 Diesel")
        if (anoModelo != null && combustivel != null) {
            String ano = String.valueOf(anoModelo);
            String combLower = combustivel.toLowerCase();

            Optional<FipeYearOptionDTO> match = Arrays.stream(years)
                    .filter(y -> y.getName() != null)
                    .filter(y -> y.getName().contains(ano))
                    .filter(y -> y.getName().toLowerCase().contains(combLower))
                    .findFirst();

            if (match.isPresent()) return match.get().getCode();
        }

        // 2) fallback: primeiro year do mesmo ano
        if (anoModelo != null) {
            String ano = String.valueOf(anoModelo);
            Optional<FipeYearOptionDTO> matchAno = Arrays.stream(years)
                    .filter(y -> y.getName() != null && y.getName().contains(ano))
                    .findFirst();

            if (matchAno.isPresent()) return matchAno.get().getCode();
        }

        // 3) fallback final: primeiro da lista
        return years[0].getCode();
    }
}
