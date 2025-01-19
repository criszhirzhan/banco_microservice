package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.dto.CuentaDTO;
import com.banco.banco_clientes.api.dto.MovimientoDTO;
import com.banco.banco_clientes.api.response.Response;
import com.banco.banco_clientes.application.services.MovimientoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movimientos")
public class MovimientoController {

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addMovimiento (@RequestBody @Valid MovimientoDTO movimientoDTO){

        log.info("** Start addMovimiento()**");
        Response response = new Response(movimientoService.addMovimiento(movimientoDTO));
        log.info("** End addMovimiento()**");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
