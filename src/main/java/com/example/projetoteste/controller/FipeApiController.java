package com.example.projetoteste.controller;

import com.example.projetoteste.domain.fipeapi.FipeAPIresponse;
import com.example.projetoteste.service.FipeApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fipe")
public class FipeApiController {

    private final FipeApiService service;

    public FipeApiController(FipeApiService service) {
        this.service = service;
    }

    @GetMapping("/{vehicleType}/brands/{brandId}/models/{modelId}/years/{yearId}")
    public FipeAPIresponse get(
            @PathVariable String vehicleType,
            @PathVariable String brandId,
            @PathVariable String modelId,
            @PathVariable String yearId
    ) {
        return service.getDetails(vehicleType, brandId, modelId, yearId);
    }
}