package com.example.projetoteste.domain.repository;

import com.example.projetoteste.domain.model.Veiculo;

import java.util.Arrays;
import java.util.List;


public class VeiculoRepository {

    public static List<Veiculo> getAll() {
        return Arrays.asList(
                new Veiculo("Golf GTI", "Volkswagen", "FWD", 4),
                new Veiculo("Civic Type R", "Honda", "FWD", 4),
                new Veiculo("Corolla GR", "Toyota", "AWD", 3),
                new Veiculo("Mustang GT", "Ford", "RWD", 8),
                new Veiculo("Camaro SS", "Chevrolet", "RWD", 8),
                new Veiculo("M3 Competition", "BMW", "RWD", 6),
                new Veiculo("AMG C63", "Mercedes-Benz", "RWD", 8),
                new Veiculo("RS3", "Audi", "AWD", 5),
                new Veiculo("WRX STI", "Subaru", "AWD", 4),
                new Veiculo("911 Carrera", "Porsche", "RWD", 6)
        );
    }

}
