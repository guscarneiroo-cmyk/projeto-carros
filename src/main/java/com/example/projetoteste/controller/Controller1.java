package com.example.projetoteste.controller;


import com.example.projetoteste.domain.model.Veiculo;
import com.example.projetoteste.service.Service1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class Controller1 {

    private final Service1 service1;

    public Controller1(Service1 service1) {
        this.service1 = service1;
    }

    @GetMapping("/{id}")
    public Veiculo getById(@PathVariable Integer id) {
        return service1.getById(id);
    }

    @GetMapping
    public List<Veiculo> listVeiculo(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String tracao,
            @RequestParam(required = false) Integer cilindros,
            @RequestParam(required = false) String modelo
    ) {
        return service1.filtrar(marca, tracao, cilindros, modelo);
    }

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody Veiculo veiculo) {
        Veiculo criado = service1.create(veiculo);

        URI location = URI.create("/veiculos/" + criado.getId());
        return ResponseEntity.created(location).body(criado);
    }

}
