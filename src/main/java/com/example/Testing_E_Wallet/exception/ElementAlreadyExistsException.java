package com.example.Testing_E_Wallet.exception;

/**
 * Custom exception class used for when the requested record is already exists
 */
public class ElementAlreadyExistsException extends  RuntimeException {
    public ElementAlreadyExistsException() {
        super();
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }

    public ElementAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
