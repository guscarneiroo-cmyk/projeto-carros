package com.example.projetoteste.domain.brasilapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrasilApiResponse {

    public List<BrasilVeiculo> veiculos;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BrasilVeiculo {

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
}
