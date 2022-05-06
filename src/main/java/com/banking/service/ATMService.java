package com.banking.service;

import com.banking.model.ATM;

import java.util.List;

public interface ATMService {

    List<ATM> getATMByIdentification(String identification);
}
