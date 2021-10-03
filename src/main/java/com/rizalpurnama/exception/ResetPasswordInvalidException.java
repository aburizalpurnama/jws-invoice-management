package com.rizalpurnama.exception;

public class ResetPasswordInvalidException extends Exception{

    public ResetPasswordInvalidException() {
    }

    public ResetPasswordInvalidException(String message) {
        super(message);
    }
}
