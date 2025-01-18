package com.banco.banco_clientes.application.exceptions;

public class RegistrationFailedException extends RuntimeException{

    public RegistrationFailedException(String message) {
        super(message);
    }

    public RegistrationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
