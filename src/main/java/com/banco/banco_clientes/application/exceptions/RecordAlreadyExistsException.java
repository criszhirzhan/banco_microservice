package com.banco.banco_clientes.application.exceptions;

public class RecordAlreadyExistsException extends RuntimeException{

    public RecordAlreadyExistsException(String message) {
        super(message);
    }

    public RecordAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
