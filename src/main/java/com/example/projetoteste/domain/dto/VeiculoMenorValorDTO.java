package com.example.projetoteste.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VeiculoMenorValorDTO {

        private String marca;
        private String modelo;
        private Integer anoModelo;
        private String combustivel;
        private String codigoFipe;
        private BigDecimal valor;
        private String mesReferencia;
        private Integer tipoVeiculo;

}
