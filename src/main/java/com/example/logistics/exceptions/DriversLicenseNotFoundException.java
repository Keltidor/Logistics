package com.example.logistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DriversLicenseNotFoundException extends RuntimeException {

    public DriversLicenseNotFoundException(String message) {
        super(message);
    }
}
