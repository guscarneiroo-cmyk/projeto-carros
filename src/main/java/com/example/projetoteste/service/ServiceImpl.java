package com.example.projetoteste.service;


import com.example.projetoteste.domain.model.Veiculo;
import com.example.projetoteste.domain.repository.VeiculoRepository;

import java.util.List;


public class ServiceImpl implements Service1 {

    // implementação do método getAllVeiculo acessando o repository
    @Override
    public List<Veiculo> getAllVeiculo() {
        return VeiculoRepository.getAll();
    }

}
