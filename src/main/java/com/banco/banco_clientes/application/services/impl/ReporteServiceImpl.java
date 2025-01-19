package com.banco.banco_clientes.application.services.impl;

import com.banco.banco_clientes.api.dto.CuentaDTO;
import com.banco.banco_clientes.api.dto.MovimientoDTO;
import com.banco.banco_clientes.api.dto.ReporteDTO;
import com.banco.banco_clientes.api.filters.ReporteFilterDTO;
import com.banco.banco_clientes.application.services.ReporteService;
import com.banco.banco_clientes.application.utils.DateUtil;
import com.banco.banco_clientes.persistence.entities.Cuenta;
import com.banco.banco_clientes.persistence.entities.Movimiento;
import com.banco.banco_clientes.persistence.repository.MovimientoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final MovimientoRepository movimientoRepository;

    public ReporteServiceImpl(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }


    @Override
    public ReporteDTO getReporteWithFilter(ReporteFilterDTO filterDTO) {

        Date fechaInicio = DateUtil.convertirAFechaInicio(filterDTO.getFechaInicio());
        Date fechaFin = DateUtil.convertirAFechaFin(filterDTO.getFechaFin());

        List<Movimiento> movimientos = movimientoRepository.findMovimientosByIdentificacionAndFecha(filterDTO.getCedula(), fechaInicio, fechaFin);

        Map<Long, CuentaDTO> cuentaDTOMap = new HashMap<>();

        for (Movimiento movimiento : movimientos) {
            Cuenta cuenta = movimiento.getCuenta();
            Long idCuenta = cuenta.getId();

            if (!cuentaDTOMap.containsKey(idCuenta)) {
                CuentaDTO cuentaDTO = new CuentaDTO();
                cuentaDTO.setId(idCuenta);
                cuentaDTO.setIdCliente(cuenta.getCliente().getId());
                cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
                cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
                cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
                cuentaDTO.setCliente(cuenta.getCliente().getNombre());
                cuentaDTO.setEstado(cuenta.getEstado());
                cuentaDTO.setMovimientoDTOList(new ArrayList<>());
                cuentaDTOMap.put(idCuenta, cuentaDTO);
            }

            MovimientoDTO movimientoDTO = new MovimientoDTO();
            movimientoDTO.setId(movimiento.getId());
            movimientoDTO.setIdCuenta(idCuenta);
            movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
            movimientoDTO.setDescripcionMovimiento(movimiento.getDescripcionMovimiento());
            movimientoDTO.setValor(movimiento.getValor());
            movimientoDTO.setSaldo(movimiento.getSaldo());
            movimientoDTO.setFecha(DateUtil.convertirFechaAFormato(movimiento.getFecha()));

            cuentaDTOMap.get(idCuenta).getMovimientoDTOList().add(movimientoDTO);
        }

        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setCuentas(new ArrayList<>(cuentaDTOMap.values()));
        return reporteDTO;
    }
}
