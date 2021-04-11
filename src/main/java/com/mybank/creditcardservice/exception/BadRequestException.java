package com.mybank.creditcardservice.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 8519290762298075002L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause.getMessage());
    }
}
