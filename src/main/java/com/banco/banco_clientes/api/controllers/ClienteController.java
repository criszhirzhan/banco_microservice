package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.filters.ClienteFilterDTO;
import com.banco.banco_clientes.api.response.Response;
import com.banco.banco_clientes.application.services.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addCliente (@RequestBody @Valid ClienteDTO cliente){

        log.info("** Start addCliente()**");
        Response response = new Response(service.addCliente(cliente));
        log.info("** End addCliente()**");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ClienteDTO>> getClientesWithFilter(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String identificacion) {

        log.info("** Start getClientesWithFilter() **");

        ClienteFilterDTO filterDTO = new ClienteFilterDTO();
        filterDTO.setNombre(nombre);
        filterDTO.setGenero(genero);
        filterDTO.setEstado(estado);
        filterDTO.setIdentificacion(identificacion);

        Page<ClienteDTO> clientes = service.getClientesWithFilter(filterDTO);
        log.info("** End getClientesWithFilter() **");

        return ResponseEntity.ok(clientes);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        log.info("** Start updateCliente() **");
        String message = service.updateCliente(id, clienteDTO);
        Response response = new Response(message);
        log.info("** End updateCliente() **");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCliente(@PathVariable Long id) {
        log.info("** Start deleteCliente() **");
        String message = service.deleteCliente(id);
        Response response = new Response(message);
        log.info("** End deleteCliente() **");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
