package com.example.projetoteste.service;

import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;
import org.springframework.stereotype.Service;

@Service
public class FipeFacadeImpl implements FipeFacade {

    private final BrasilApiServiceImpl brasilApiService;

    public FipeFacadeImpl(BrasilApiServiceImpl brasilApiService) {
        this.brasilApiService = brasilApiService;
    }

    @Override
    public VeiculoMenorValorDTO buscarMenorValor(String codigoFipe) {
        return brasilApiService.buscarMenorValor(codigoFipe);
    }
}