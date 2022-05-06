package com.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ATMResponse {

    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("data")
    private List<ATMData> data;

    public ATMResponse() {
    }
}
