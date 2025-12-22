package com.example.projetoteste.domain.repository;

import com.example.projetoteste.domain.model.Veiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class VeiculoRepository {

    private final List<Veiculo> veiculos = new ArrayList<>();

    public VeiculoRepository() {
        veiculos.add(new Veiculo(1, "Golf GTI", "Volkswagen", "FWD", 4));
        veiculos.add(new Veiculo(2, "Civic Type R", "Honda", "FWD", 4));
        veiculos.add(new Veiculo(3, "Corolla GR", "Toyota", "AWD", 3));
        veiculos.add(new Veiculo(4, "Mustang GT", "Ford", "RWD", 8));
        veiculos.add(new Veiculo(5, "Camaro SS", "Chevrolet", "RWD", 8));
        veiculos.add(new Veiculo(6, "M3 Competition", "BMW", "RWD", 6));
        veiculos.add(new Veiculo(7, "AMG C63", "Mercedes-Benz", "RWD", 8));
        veiculos.add(new Veiculo(8, "RS3", "Audi", "AWD", 5));
        veiculos.add(new Veiculo(9, "WRX STI", "Subaru", "AWD", 4));
        veiculos.add(new Veiculo(10, "911 Carrera", "Porsche", "RWD", 6));
    }

    public List<Veiculo> findAll() {
        return veiculos;
    }

    public Veiculo buscarPorId(Integer id) {
        return veiculos.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Veiculo save(Veiculo veiculo) {
        veiculos.add(veiculo);
        return veiculo;
    }

    public int proximoId() {
        return veiculos.stream()
                .mapToInt(Veiculo::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<Veiculo> buscarPorMarca(String marca) {
        if (marca == null || marca.isBlank()) return List.of();

        return veiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    public List<Veiculo> buscarPorTracao(String tracao) {
        if (tracao == null || tracao.isBlank()) return List.of();

        return veiculos.stream()
                .filter(v ->  v.getTracao().equalsIgnoreCase(tracao))
                .toList();
    }

    public List<Veiculo> buscarPorCilindros(Integer cilindros) {
        if (cilindros == null) return List.of();

        return veiculos.stream()
                .filter(v -> v.getCilindros() == cilindros)
                .toList();
    }

    public List<Veiculo> buscarPorModeloContem(String trecho) {
        if (trecho == null || trecho.isBlank()) return List.of();

        String t = trecho.toLowerCase();
        return veiculos.stream()
                .filter(v -> v.getModelo().toLowerCase().contains(t))
                .toList();
    }

    public List<Veiculo> buscarComFiltros(String marca, String tracao, Integer cilindros, String modelo) {
        return veiculos.stream()
                .filter(v -> marca == null || marca.isBlank() || v.getMarca().equalsIgnoreCase(marca))
                .filter(v -> tracao == null || tracao.isBlank() || v.getTracao().equalsIgnoreCase(tracao))
                .filter(v -> cilindros == null || v.getCilindros() == cilindros)
                .filter(v -> modelo == null || modelo.isBlank() || v.getModelo().toLowerCase().contains(modelo.toLowerCase()))
                .toList();
    }



}
