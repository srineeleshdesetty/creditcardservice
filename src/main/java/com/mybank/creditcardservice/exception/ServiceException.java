package com.mybank.creditcardservice.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2923702428509413736L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause.getMessage());
    }
}
