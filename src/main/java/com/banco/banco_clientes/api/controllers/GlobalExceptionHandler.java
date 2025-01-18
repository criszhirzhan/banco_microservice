package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.response.Response;
import com.banco.banco_clientes.application.exceptions.EmptyDataException;
import com.banco.banco_clientes.application.exceptions.ErrorDetail;
import com.banco.banco_clientes.application.utils.Messages;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String ERROR = "Error: {}, {}";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response generalExceptionHandler(Exception ex, HttpServletRequest req){

        log.error(ERROR, ex.getMessage(), req.getRequestURI());

        return Response.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Consulte con el administrador ha surgido un inconveniente")
                .build();
    }
    @ExceptionHandler(EmptyDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response emptyDataExceptionHandler(EmptyDataException ex, WebRequest req){

        log.error(ERROR, ex.getMessage(), req);

        return Response.builder()
                .code(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorDetail> errorDetails = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessageLocal = error.getDefaultMessage();
            errorDetails.add(new ErrorDetail(fieldName, errorMessageLocal));
        });

        return new Response(Messages.EEROR,HttpStatus.BAD_REQUEST.value(), Messages.VALID_ERROR, errorDetails);
    }

}
