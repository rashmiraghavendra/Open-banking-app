package com.banking.service;

import com.banking.exception.ServiceException;
import com.banking.model.ATM;
import com.banking.repository.ATMRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ATMFinderService implements ATMService {

    private final ATMRepository atmRepository;

    public ATMFinderService(ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @Override
    public List<ATM> getATMByIdentification(String identification) {
        var atms = atmRepository.getATMs();
        var atmData = Optional.ofNullable(atms.getData()).orElseThrow(ServiceException::new);

        return atmData.stream()
                .flatMap(brand -> brand.getBrands().stream())
                .flatMap(data -> data.getAtms().stream())
                .filter(atm -> atm.getIdentification().equals(identification))
                .collect(Collectors.toList());
    }
}
