package com.example.Testing_E_Wallet.exception;
/**
 * Custom exception class thrown when transfer amount is higher than account balance of sender
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
