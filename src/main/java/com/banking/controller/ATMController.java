package com.banking.controller;

import com.banking.model.ATM;
import com.banking.service.ATMService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ATMController {

    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/atms")
    private List<ATM> getATMByIdentifier(@RequestParam String identification) {
        return atmService.getATMByIdentification(identification);
    }

}
