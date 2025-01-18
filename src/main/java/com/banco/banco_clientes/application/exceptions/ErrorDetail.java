package com.banco.banco_clientes.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {
    private String field;
    private String message;
}
