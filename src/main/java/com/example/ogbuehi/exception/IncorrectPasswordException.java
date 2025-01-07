package com.example.ogbuehi.exception;
/**
 * Custom exception class thrown when password is incorrect
 */
public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super();
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
