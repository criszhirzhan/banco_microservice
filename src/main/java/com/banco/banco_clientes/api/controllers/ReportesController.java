package com.banco.banco_clientes.api.controllers;

import com.banco.banco_clientes.api.dto.ReporteDTO;
import com.banco.banco_clientes.api.filters.ReporteFilterDTO;
import com.banco.banco_clientes.application.services.ReporteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reportes")
public class ReportesController {

    private static final Logger log = LoggerFactory.getLogger(ReportesController.class);

    private final ReporteService reporteService;

    public ReportesController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReporteDTO> getReporteWithFilter(
            @RequestParam(required = false) String cedula,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin) {

        log.info("** Start getReporteWithFilter() **");

        ReporteFilterDTO filterDTO = new ReporteFilterDTO();
        filterDTO.setCedula(cedula);
        filterDTO.setFechaInicio(fechaInicio);
        filterDTO.setFechaFin(fechaFin);

        ReporteDTO reporte = reporteService.getReporteWithFilter(filterDTO);
        log.info("** End getReporteWithFilter() **");

        return ResponseEntity.ok(reporte);
    }

}
