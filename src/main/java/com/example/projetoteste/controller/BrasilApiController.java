package com.example.projetoteste.controller;

import com.example.projetoteste.domain.dto.VeiculoFaixaValorDTO;
import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;
import com.example.projetoteste.service.BrasilApiServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brasil-api")
public class BrasilApiController {

    private final BrasilApiServiceImpl brasilApiService;

    public BrasilApiController (BrasilApiServiceImpl brasilApiService) {
        this.brasilApiService = brasilApiService;
    }

    @GetMapping("/{codigoFipe}")
    public VeiculoMenorValorDTO menorValor (@PathVariable String codigoFipe) {
        return brasilApiService.buscarMenorValor(codigoFipe);
    }

    @GetMapping("/{codigoFipe}/faixa")
    public VeiculoFaixaValorDTO faixa(@PathVariable String codigoFipe) {
        return brasilApiService.buscarFaixaValores(codigoFipe);
    }

}
