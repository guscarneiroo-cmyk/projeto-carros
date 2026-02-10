package com.example.projetoteste.controller;

import com.example.projetoteste.domain.dto.VehicleProfileResponse;
import com.example.projetoteste.service.VehicleProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleProfileController {

    private final VehicleProfileService vehicleProfileService;

    public VehicleProfileController(VehicleProfileService vehicleProfileService) {
        this.vehicleProfileService = vehicleProfileService;
    }

    @GetMapping("/profile")
    public VehicleProfileResponse profile(
            @RequestParam String vin,
            @RequestParam String fipeCode
    ) {
        return vehicleProfileService.getProfile(vin, fipeCode);
    }
}