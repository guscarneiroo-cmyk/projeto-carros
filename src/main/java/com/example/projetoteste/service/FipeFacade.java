package com.example.projetoteste.service;

import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;

public interface FipeFacade {
    VeiculoMenorValorDTO buscarMenorValor(String codigoFipe);
}