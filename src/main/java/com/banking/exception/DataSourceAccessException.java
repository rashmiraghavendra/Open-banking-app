package com.banking.exception;

public class DataSourceAccessException extends RuntimeException {

    public DataSourceAccessException(Exception exception) {
        super(exception);
    }

}
