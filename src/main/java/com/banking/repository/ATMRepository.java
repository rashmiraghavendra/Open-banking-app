package com.banking.repository;

import com.banking.exception.DataAccessException;
import com.banking.model.ATMResponse;
import com.banking.util.BasicReadWebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class ATMRepository {

    @Value("${open.banking.url}")
    private String url;

    private final BasicReadWebClient basicReadWebClient;

    public ATMRepository(BasicReadWebClient basicReadWebClient) {
        this.basicReadWebClient = basicReadWebClient;
    }

    @Cacheable("atmResponse")
    public ATMResponse getATMs() throws DataAccessException {
        var typeReference = new ParameterizedTypeReference<ATMResponse>() {
        };
        return basicReadWebClient.getData(url, typeReference);
    }

}
