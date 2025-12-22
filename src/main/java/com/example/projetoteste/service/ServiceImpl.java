package com.example.projetoteste.service;


import com.example.projetoteste.domain.model.Veiculo;
import com.example.projetoteste.domain.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServiceImpl implements Service1 {

    private final VeiculoRepository veiculoRepository;

    public ServiceImpl(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    // implementação do método getAllVeiculo acessando o repository
    @Override
    public List<Veiculo> getAllVeiculo() {
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo getById(Integer id) {
        Veiculo veiculo = veiculoRepository.buscarPorId(id);

        if (veiculo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }

        return veiculo;
    }

    @Override
    public List<Veiculo> getByMarca(String marca) {
        return veiculoRepository.buscarPorMarca(marca);
    }

    @Override
    public List<Veiculo> getByTracao(String tracao) {
        return veiculoRepository.buscarPorTracao(tracao);
    }

    @Override
    public List<Veiculo> getByCilindros(Integer cilindros) {
        return veiculoRepository.buscarPorCilindros(cilindros);
    }

    @Override
    public List<Veiculo> searchByModelo(String trecho) {
        return veiculoRepository.buscarPorModeloContem(trecho);
    }

    @Override
    public List<Veiculo> filtrar(String marca, String tracao, Integer cilindros, String modelo) {
        return veiculoRepository.buscarComFiltros(marca, tracao, cilindros, modelo);
    }

    @Override
    public Veiculo create(Veiculo veiculo) {
        if (veiculo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body inválido");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Modelo é obrigatório");
        }

        if (veiculo.getMarca() == null || veiculo.getMarca().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Marca é obrigatória");
        }

        if (veiculo.getTracao() == null || veiculo.getTracao().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tração é obrigatória");
        }

        if (veiculo.getCilindros() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cilindros inválido");
        }

        int novoId = veiculoRepository.proximoId();
        veiculo.setId(novoId);

        return veiculoRepository.save(veiculo);
    }

}