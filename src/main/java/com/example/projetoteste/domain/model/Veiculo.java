package com.example.projetoteste.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Veiculo {

    private Integer id;
    private String modelo;
    private String marca;
    private String tracao;
    private Integer cilindros;

    public Veiculo(Integer id, String modelo, String marca, String tracao, Integer cilindros) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.tracao = tracao;
        this.cilindros = cilindros;
    }

}
