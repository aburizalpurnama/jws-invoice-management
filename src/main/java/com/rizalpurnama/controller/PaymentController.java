package com.rizalpurnama.controller;

import com.rizalpurnama.exception.VirtualAccountAlreadyPaidException;
import com.rizalpurnama.exception.VirtualAccountNotFoundException;
import com.rizalpurnama.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void pay() throws VirtualAccountAlreadyPaidException, VirtualAccountNotFoundException {
        paymentService.pay(null,
                null,
                null,
                null,
                null);
    }
}
