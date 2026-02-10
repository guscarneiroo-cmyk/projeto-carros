package com.example.projetoteste.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinResponse {

    @JsonProperty("make")
    private String make;

    @JsonProperty("model")
    private String model;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    @JsonProperty("class")
    private String vehicleClass;

    @JsonProperty("wmi")
    private String wmi;

    @JsonProperty("vds")
    private String vds;

    @JsonProperty("vis")
    private String vis;

    @JsonProperty("year")
    private Integer year;
}
