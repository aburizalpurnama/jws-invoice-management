package com.rizalpurnama.exception;

public class VirtualAccountAlreadyPaidException extends Exception{

    public VirtualAccountAlreadyPaidException() {
        super();
    }

    public VirtualAccountAlreadyPaidException(String message) {
        super(message);
    }
}
