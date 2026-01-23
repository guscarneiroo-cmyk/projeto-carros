package com.example.projetoteste.domain.fipeapi;

public enum FipeVehicleType {

    CARS("cars", 1),
    MOTORCYCLES("motorcycles", 2),
    TRUCKS("trucks", 3);

    private final String path;
    private final Integer brasilTipoVeiculo;

    FipeVehicleType(String path, Integer brasilTipoVeiculo) {
        this.path = path;
        this.brasilTipoVeiculo = brasilTipoVeiculo;
    }

    public String getPath() {
        return path;
    }

    public static FipeVehicleType fromBrasilTipoVeiculo(Integer tipoVeiculo) {
        if (tipoVeiculo == null) throw new IllegalArgumentException("tipoVeiculo veio nulo da BrasilAPI");
        for (FipeVehicleType t : values()) {
            if (t.brasilTipoVeiculo == tipoVeiculo) return t;
        }
        throw new IllegalArgumentException("tipoVeiculo inválido da BrasilAPI: " + tipoVeiculo);
    }
}
