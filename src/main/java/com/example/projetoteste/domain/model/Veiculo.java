package com.example.projetoteste.domain.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Veiculo {

    private String modelo;
    private String marca;
    private String tracao;
    private int cilindros;

    public Veiculo(String modelo, String marca, String tracao, int cilindros) {
        this.modelo = modelo;
        this.marca = marca;
        this.tracao = tracao;
        this.cilindros = cilindros;
    }




}
