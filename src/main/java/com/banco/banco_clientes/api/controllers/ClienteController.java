package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.response.Response;
import com.banco.banco_clientes.application.services.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping(path = "/cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addCustomer (@RequestBody @Valid ClienteDTO cliente){

        log.info("** Start cliente()**");
        Response response = new Response();
        log.info("** End cliente()**");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
