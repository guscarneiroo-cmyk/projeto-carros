package com.example.projetoteste.controller;


import com.example.projetoteste.domain.model.Veiculo;
import com.example.projetoteste.service.Service1;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller1 {

    private final Service1 service1;

    public Controller1(Service1 service1) {
        this.service1 = service1;
    }

    // chama o método getAllVeiculo da classe service
    @GetMapping("/veiculos")
    public List<Veiculo> listVeiculo() {
        return service1.getAllVeiculo();
    }


}
