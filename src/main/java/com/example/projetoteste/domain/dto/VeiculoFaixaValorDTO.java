package com.example.projetoteste.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VeiculoFaixaValorDTO {

    private BigDecimal menor;
    private BigDecimal maior;
    private BigDecimal media;
    private Integer quantidade;

}