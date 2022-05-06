package com.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ATM {

    @JsonProperty("Identification")
    private String identification;

    @JsonProperty("SupportedCurrencies")
    private List<String> supportedCurrencies;

    @JsonProperty("Location")
    private Location location;

    public ATM() {
    }

}
