package com.example.projetoteste.service;

import com.example.projetoteste.domain.brasilapi.BrasilApiResponse;
import com.example.projetoteste.domain.dto.BrasilAPIveiculoDTO;
import com.example.projetoteste.domain.dto.VeiculoMenorValorDTO;
import com.example.projetoteste.domain.fipeapi.FipeYearOptionDTO;
import com.example.projetoteste.domain.merged.BrasilFipeMergeResponse;
import com.example.projetoteste.domain.fipeapi.FipeVehicleType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MergedServiceImpl {

    private final BrasilApiServiceImpl brasilApiService;
    private final FipeParallelumService parallelumService;

    public MergedServiceImpl(
            BrasilApiServiceImpl brasilApiService,
            FipeParallelumService parallelumService
    ) {
        this.brasilApiService = brasilApiService;
        this.parallelumService = parallelumService;
    }

    public BrasilFipeMergeResponse mergeByCodigoFipe(String codigoFipe) {

        VeiculoMenorValorDTO brasilMenorValorResp = brasilApiService.buscarMenorValor(codigoFipe);

        VeiculoMenorValorDTO brasil = brasilMenorValorResp;

        FipeVehicleType vehicleType = FipeVehicleType.fromBrasilTipoVeiculo(brasil.getTipoVeiculo());
        String vehicleTypePath = vehicleType.getPath();

        FipeYearOptionDTO[] years = parallelumService.listYears(vehicleTypePath, codigoFipe);

        String yearId = parallelumService.resolveYearId(years, brasil.getAnoModelo(), brasil.getCombustivel());
        if (yearId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Parallelum não retornou anos para vehicleType=" + vehicleTypePath + " e fipeCode=" + codigoFipe);
        }

        var detail = parallelumService.getDetailByYear(vehicleTypePath, codigoFipe, yearId);

        BrasilFipeMergeResponse out = new BrasilFipeMergeResponse();
        out.setCodigoFipe(codigoFipe);
        out.setVehicleType(vehicleTypePath);
        out.setYearId(yearId);
        out.setBrasil(brasil);
        out.setParallelum(detail);

        return out;
    }

}
