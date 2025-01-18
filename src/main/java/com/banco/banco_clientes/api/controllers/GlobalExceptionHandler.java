package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.response.Response;
import com.banco.banco_clientes.application.exceptions.EmptyDataException;
import com.banco.banco_clientes.application.exceptions.ErrorDetail;
import com.banco.banco_clientes.application.exceptions.RecordAlreadyExistsException;
import com.banco.banco_clientes.application.exceptions.RegistrationFailedException;
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
    @ResponseBody
    public Response generalExceptionHandler(Exception ex, HttpServletRequest req){

        log.error(ERROR, ex.getMessage(), req.getRequestURI());

        return Response.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .response(Messages.ERROR)
                .message(Messages.GENERAL_ERROR)
                .build();
    }
    @ExceptionHandler(EmptyDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Response emptyDataExceptionHandler(EmptyDataException ex, WebRequest req){

        log.error(ERROR, ex.getMessage(), req);

        return Response.builder()
                .code(HttpStatus.CONFLICT.value())
                .response(Messages.ERROR)
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

        return new Response(Messages.ERROR,HttpStatus.BAD_REQUEST.value(), Messages.VALID_ERROR, errorDetails);
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Response RecordAlreadyExistsException(RecordAlreadyExistsException ex, HttpServletRequest req){

        log.error(ERROR, ex.getMessage(), req.getRequestURI());

        return Response.builder()
                .code(HttpStatus.CONFLICT.value())
                .response(Messages.ERROR)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(RegistrationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleRegistrationFailedException(RegistrationFailedException ex, WebRequest req) {
        Response errorResponse = new Response();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());

        log.error(ERROR, ex.getMessage(), req);
        return Response.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .response(Messages.ERROR)
                .message(ex.getMessage())
                .build();
    }

}
