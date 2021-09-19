package com.rizalpurnama.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvoiceTypeNotFoundException extends Exception {
    public InvoiceTypeNotFoundException() {
    }

    public InvoiceTypeNotFoundException(String message) {
        super(message);
    }
}
