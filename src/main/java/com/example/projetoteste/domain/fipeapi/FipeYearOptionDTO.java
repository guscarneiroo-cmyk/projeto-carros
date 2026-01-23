package com.example.projetoteste.domain.fipeapi;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FipeYearOptionDTO {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

}
