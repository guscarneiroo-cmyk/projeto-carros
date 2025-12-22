package com.example.projetoteste.service;

import com.example.projetoteste.domain.model.Veiculo;
import com.example.projetoteste.domain.repository.VeiculoRepository;

import java.util.List;

public interface Service1 {

   // declarado os GETs
   List<Veiculo> getAllVeiculo();

   Veiculo getById(Integer id);

   List<Veiculo> getByMarca(String marca);
   List<Veiculo> getByTracao(String tracao);
   List<Veiculo> getByCilindros(Integer cilindros);
   List<Veiculo> searchByModelo(String trecho);

   List<Veiculo> filtrar(String marca, String tracao, Integer cilindros, String modelo);

   Veiculo create(Veiculo veiculo);
}
