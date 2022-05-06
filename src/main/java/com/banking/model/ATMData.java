package com.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ATMData {

    @JsonProperty("Brand")
    private List<Brand> brands;

    public ATMData() {
    }
}
