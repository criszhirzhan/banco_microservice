package com.banco.banco_clientes.application.exceptions;

public class BalanceNotAvailableException extends RuntimeException{

    public BalanceNotAvailableException(String message) {
        super(message);
    }

    public BalanceNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
