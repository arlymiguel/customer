package com.devsu.customer.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Data
public class ClientNotFoundException extends RuntimeException {

    private final HttpStatus statusCode;


    public ClientNotFoundException(final String statusMessage) {
        this(HttpStatus.NOT_FOUND, statusMessage);
    }

    public ClientNotFoundException(final HttpStatus statusCode, final String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
    }

}
