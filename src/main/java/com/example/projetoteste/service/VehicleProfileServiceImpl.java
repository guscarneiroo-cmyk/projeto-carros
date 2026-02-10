package com.example.projetoteste.service;

import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;
import com.example.projetoteste.domain.dto.VinDTO;
import com.example.projetoteste.domain.dto.VehicleProfileResponse;
import org.springframework.stereotype.Service;

@Service
public class VehicleProfileServiceImpl implements VehicleProfileService {

    private final VinService vinService;
    private final FipeFacade fipeFacade;

    public VehicleProfileServiceImpl(VinService vinService, FipeFacade fipeFacade) {
        this.vinService = vinService;
        this.fipeFacade = fipeFacade;
    }

    @Override
    public VehicleProfileResponse getProfile(String vin, String codigoFipe) {
        VinDTO vinData = vinService.buscaVin(vin);
        VeiculoMenorValorDTO fipeData = fipeFacade.buscarMenorValor(codigoFipe);

        return new VehicleProfileResponse(vinData, fipeData);
    }
}