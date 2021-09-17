package com.rizalpurnama.exception;

public class VirtualAccountNotFoundException extends Exception{
    public VirtualAccountNotFoundException() {
        super();
    }

    public VirtualAccountNotFoundException(String message) {
        super(message);
    }
}
