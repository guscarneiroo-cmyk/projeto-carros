package com.example.projetoteste.controller;

import com.example.projetoteste.domain.merged.BrasilFipeMergeResponse;
import com.example.projetoteste.service.MergedService;
import com.example.projetoteste.service.MergedServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merged")
public class MergeController {


    private final MergedServiceImpl service;

    public MergeController(MergedServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/validar/{codigoFipe}")
    public BrasilFipeMergeResponse validar(@PathVariable String codigoFipe) {
        return service.mergeByCodigoFipe(codigoFipe);
    }

}
