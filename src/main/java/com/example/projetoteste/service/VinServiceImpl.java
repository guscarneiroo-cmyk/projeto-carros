package com.example.projetoteste.service;

import com.example.projetoteste.client.VinClient;
import com.example.projetoteste.domain.dto.VinDTO;
import com.example.projetoteste.domain.dto.VinResponse;
import com.example.projetoteste.exception.InvalidVinException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class VinServiceImpl implements VinService {

    private final VinClient client;

    public VinServiceImpl(VinClient client) {
        this.client = client;
    }

    @Override
    public VinDTO buscaVin(String vin) {
        String normalized = normalizeVin(vin);
        validateVin(normalized);

        VinResponse external = client.buscaVin(normalized);

        return mapToInternal(normalized, external);
    }

    private VinDTO mapToInternal(String vin, VinResponse ext) {
        return new VinDTO(
                vin,
                ext.getMake(),
                ext.getModel(),
                ext.getYear(),
                ext.getVehicleClass(),
                ext.getCountry(),
                ext.getRegion(),
                ext.getWmi(),
                ext.getVds(),
                ext.getVis()
        );
    }

    private String normalizeVin(String vin) {
        if (vin == null) return null;
        return vin.trim().toUpperCase(Locale.ROOT);
    }

    private void validateVin(String vin) {
        if (vin == null || vin.isBlank()) {
            throw new InvalidVinException("VIN não pode ser vazio.");
        }
        if (vin.length() != 17) {
            throw new InvalidVinException("VIN deve ter 17 caracteres.");
        }
        if (vin.matches(".*[IOQ].*")) {
            throw new InvalidVinException("VIN inválido: não pode conter I, O ou Q.");
        }
        if (!vin.matches("^[A-Z0-9]{17}$")) {
            throw new InvalidVinException("VIN inválido: use apenas letras e números (17 chars).");
        }
    }
}
