package com.example.projetoteste.service;

import com.example.projetoteste.domain.fipeapi.FipeAPIresponse;

public interface FipeApiService {

    FipeAPIresponse getDetails(
            String vehicleType,
            String brandId,
            String modelId,
            String yearId
    );
}