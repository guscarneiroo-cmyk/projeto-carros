package com.example.projetoteste.domain.merged;

import com.example.projetoteste.domain.brasilapi.BrasilApiResponse;
import com.example.projetoteste.domain.fipeapi.FipeAPIresponse;
import lombok.Data;

@Data
public class MergedMenorValorDTO {

    private BrasilApiResponse.BrasilVeiculo brasil;

    private FipeAPIresponse fipeMin;

    private String fipeMinYearId;

    private String codigoFipe;

    private String vehicleType;

}
