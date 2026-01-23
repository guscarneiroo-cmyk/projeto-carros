package com.example.projetoteste.service;

import com.example.projetoteste.domain.dto.BrasilAPIveiculoDTO;
import com.example.projetoteste.domain.dto.VeiculoFaixaValorDTO;
import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;

public interface BrasilApiService {

    VeiculoMenorValorDTO buscarMenorValor(String codigoFipe);

    VeiculoFaixaValorDTO buscarFaixaValores(String codigoFipe);
}
