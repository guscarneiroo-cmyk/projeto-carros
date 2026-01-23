package com.example.projetoteste.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrasilAPIveiculoDTO {

    @JsonProperty("valor")
    private String valor;

    @JsonProperty("marca")
    private String marca;

    @JsonProperty("modelo")
    private String modelo;

    @JsonProperty("anoModelo")
    private Integer anoModelo;

    @JsonProperty("combustivel")
    private String combustivel;

    @JsonProperty("codigoFipe")
    private String codigoFipe;

    @JsonProperty("mesReferencia")
    private String mesReferencia;

    @JsonProperty("tipoVeiculo")
    private Integer tipoVeiculo;

    @JsonProperty("siglaCombustivel")
    private String siglaCombustivel;

    @JsonProperty("dataConsulta")
    private String dataConsulta;
}