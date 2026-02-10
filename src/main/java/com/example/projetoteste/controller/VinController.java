package com.example.projetoteste.controller;

import com.example.projetoteste.domain.dto.VinDTO;
import com.example.projetoteste.domain.dto.VinResponse;
import com.example.projetoteste.service.VinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vinlookup")
public class VinController {

    private final VinService service;

    public VinController(VinService service) {
        this.service = service;
    }

    @GetMapping("/{vin}")
    public VinDTO buscaVin(@PathVariable String vin) {
        return service.buscaVin(vin);
    }
}
