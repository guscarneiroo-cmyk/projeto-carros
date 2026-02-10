package com.example.projetoteste.service;

import com.example.projetoteste.domain.dto.VehicleProfileResponse;

public interface VehicleProfileService {
    VehicleProfileResponse getProfile(String vin, String codigoFipe);
}