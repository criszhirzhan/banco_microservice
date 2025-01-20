package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.dto.CuentaDTO;
import com.banco.banco_clientes.api.filters.ClienteFilterDTO;
import com.banco.banco_clientes.api.filters.CuentaFilterDTO;
import com.banco.banco_clientes.api.response.Response;
import com.banco.banco_clientes.application.services.CuentaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cuentas")
public class CuentaController {
    private static final Logger log = LoggerFactory.getLogger(CuentaController.class);

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addCuenta (@RequestBody @Valid CuentaDTO cuentaDTO){

        log.info("** Start addCuenta()**");
        Response response = new Response(cuentaService.addCuenta(cuentaDTO));
        log.info("** End addCuenta()**");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CuentaDTO>> getCuentasWithFilter(
            @RequestParam(required = false) String cedula,
            @RequestParam(required = false) String numeroCuenta,
            @RequestParam(required = false) String tipoCuenta,
            @RequestParam(required = false) String estado) {

        log.info("** Start getCuentasWithFilter() **");

        CuentaFilterDTO filterDTO = new CuentaFilterDTO();
        filterDTO.setCedula(cedula);
        filterDTO.setNumeroCuenta(numeroCuenta);
        filterDTO.setEstado(estado);
        filterDTO.setTipoCuenta(tipoCuenta);

        Page<CuentaDTO> cuentas = cuentaService.getCuentasWithFilter(filterDTO);
        log.info("** End getCuentasWithFilter() **");

        return ResponseEntity.ok(cuentas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCuenta(@PathVariable Long id) {
        log.info("** Start deleteCuenta() **");
        String message = cuentaService.deleteCuenta(id);
        Response response = new Response(message);
        log.info("** End deleteCuenta() **");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
