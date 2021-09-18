package com.rizalpurnama.exception;

public class PaymentExceedInvoiceAmountException extends Exception{
    public PaymentExceedInvoiceAmountException() {
        super();
    }

    public PaymentExceedInvoiceAmountException(String message) {
        super(message);
    }
}
