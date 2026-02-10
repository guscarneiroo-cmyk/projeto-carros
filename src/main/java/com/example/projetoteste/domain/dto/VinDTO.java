package com.example.projetoteste.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VinDTO {

    private String vin;

    private String make;
    private String model;
    private Integer year;

    private String vehicleClass;

    private String country;
    private String region;

    private String wmi;
    private String vds;
    private String vis;

    public VinDTO() {
    }

    public VinDTO(
            String vin,
            String make,
            String model,
            Integer year,
            String vehicleClass,
            String country,
            String region,
            String wmi,
            String vds,
            String vis
    ) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.vehicleClass = vehicleClass;
        this.country = country;
        this.region = region;
        this.wmi = wmi;
        this.vds = vds;
        this.vis = vis;
    }

}
