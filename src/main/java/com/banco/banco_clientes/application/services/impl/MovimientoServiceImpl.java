package com.banco.banco_clientes.application.services.impl;

import com.banco.banco_clientes.api.dto.MovimientoDTO;
import com.banco.banco_clientes.application.exceptions.BalanceNotAvailableException;
import com.banco.banco_clientes.application.exceptions.NotFoundEntityException;
import com.banco.banco_clientes.application.exceptions.RegistrationFailedException;
import com.banco.banco_clientes.application.services.MovimientoService;
import com.banco.banco_clientes.application.utils.DateUtil;
import com.banco.banco_clientes.application.utils.Messages;
import com.banco.banco_clientes.persistence.entities.Cuenta;
import com.banco.banco_clientes.persistence.entities.Movimiento;
import com.banco.banco_clientes.persistence.repository.CuentaRepository;
import com.banco.banco_clientes.persistence.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public String addMovimiento(MovimientoDTO movimientoDTO) {

        Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getIdCuenta()).orElseThrow(() -> new NotFoundEntityException(Messages.CLIENT_NOT_FOUND));

        if (cuenta.getSaldoInicial() + movimientoDTO.getValor() < 0) {
            throw new BalanceNotAvailableException(Messages.BALANCE_NOT_AVAILABLE);
        }

        Movimiento movimiento = new Movimiento();
        if (movimientoDTO.getValor() > 0) {
            movimiento.setTipoMovimiento(Messages.DEPOSIT);
            movimiento.setDescripcionMovimiento(String.format(Messages.DEPOSIT_OF, movimientoDTO.getValor().toString()));
        } else {
            movimiento.setTipoMovimiento(Messages.WITHDRAW);
            movimiento.setDescripcionMovimiento(String.format(Messages.WITHDRAW_OF, Math.abs(movimientoDTO.getValor()))); // Usamos Math.abs() para obtener el valor positivo

        }
        movimiento.setCuenta(cuenta);
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setSaldo(cuenta.getSaldoInicial() + movimientoDTO.getValor());

        cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimientoDTO.getValor());


        movimiento.setFecha(DateUtil.getCurrentTimestamp());


        try {
            cuentaRepository.save(cuenta);
            movimientoRepository.save(movimiento);
            return Messages.SUCCESSFUL_MOVE;
        } catch (Exception e) {
            throw new RegistrationFailedException(Messages.ERROR_MOVE);
        }

    }
}
