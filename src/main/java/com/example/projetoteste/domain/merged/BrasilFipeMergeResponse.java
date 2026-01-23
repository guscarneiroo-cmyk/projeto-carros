package com.example.projetoteste.domain.merged;

import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;
import com.example.projetoteste.domain.fipeapi.FipeAPIresponse;
import lombok.Data;

@Data
public class BrasilFipeMergeResponse {

    private String codigoFipe;
    private String vehicleType;
    private String yearId;

    private VeiculoMenorValorDTO brasil;
    private FipeAPIresponse parallelum;

}
