package com.example.projetoteste.domain.fipeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FipeAPIresponse {

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("codeFipe")
    private String codeFipe;

    @JsonProperty("fuel")
    private String fuel;

    @JsonProperty("fuelAcronym")
    private String fuelAcronym;

    @JsonProperty("model")
    private String model;

    @JsonProperty("modelYear")
    private Integer modelYear;

    @JsonProperty("price")
    private String price;

    @JsonProperty("priceHistory")
    private List<PriceHistoryItem> priceHistory;

    @JsonProperty("referenceMonth")
    private String referenceMonth;

    @JsonProperty("vehicleType")
    private Integer vehicleType;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PriceHistoryItem {

        @JsonProperty("month")
        private String month;

        @JsonProperty("price")
        private String price;

        @JsonProperty("reference")
        private String reference;
    }
}