package com.banking.service;

import com.banking.model.ATM;
import com.banking.model.ATMData;
import com.banking.model.Brand;
import com.banking.repository.ATMRepository;

import java.util.ArrayList;
import java.util.List;

public class BasicATMService implements ATMService {

    private final ATMRepository atmRepository;

    public BasicATMService(ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @Override
    public List<ATM> getATMByIdentification(String identification) {
        var allATMs = atmRepository.getATMs();
        var allATMData = allATMs.getData();

        List<ATM> foundATMs = new ArrayList<>();

        for (ATMData atmData : allATMData) {
            var brands = atmData.getBrands();
            for (Brand brand : brands) {
                var atms = brand.getAtms();
                for (ATM atm : atms) {
                    if (atm.getIdentification().equals(identification)) {
                        if (atm != null) {
                            foundATMs.add(atm);
                        }
                    }
                }
            }
        }
        return foundATMs;
    }
}
