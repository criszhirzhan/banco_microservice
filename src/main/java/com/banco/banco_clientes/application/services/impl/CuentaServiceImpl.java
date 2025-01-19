package com.banco.banco_clientes.application.services.impl;

import com.banco.banco_clientes.api.dto.CuentaDTO;
import com.banco.banco_clientes.api.filters.CuentaFilterDTO;
import com.banco.banco_clientes.application.exceptions.NotFoundEntityException;
import com.banco.banco_clientes.application.exceptions.RecordAlreadyExistsException;
import com.banco.banco_clientes.application.exceptions.RegistrationFailedException;
import com.banco.banco_clientes.application.services.CuentaService;
import com.banco.banco_clientes.application.utils.Messages;
import com.banco.banco_clientes.persistence.entities.Cliente;
import com.banco.banco_clientes.persistence.entities.Cuenta;
import com.banco.banco_clientes.persistence.repository.ClienteRepository;
import com.banco.banco_clientes.persistence.repository.CuentaRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public String addCuenta(CuentaDTO cuentaDTO) {

        if(cuentaRepository.findByNumeroCuenta(cuentaDTO.getNumeroCuenta()).isPresent()){
            throw new RecordAlreadyExistsException(Messages.ACCOUNT_ALREADY_EXIST);
        }

        Cliente cliente = clienteRepository.findById(cuentaDTO.getIdCliente()).orElseThrow(() -> new NotFoundEntityException(Messages.CLIENT_NOT_FOUND));

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setCliente(cliente);
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.getEstado());

        try {
            cuentaRepository.save(cuenta);
            return Messages.REGISTER_SUCCESSFUL;
        } catch (Exception e) {
            throw new RegistrationFailedException(Messages.REGISTER_FAILED);
        }
    }

    @Override
    public Page<CuentaDTO> getCuentasWithFilter(CuentaFilterDTO filterDTO) {
        Pageable pageable = filterDTO.toPageable();
        return cuentaRepository.findCuentasWithFilter(filterDTO.getCedula(), filterDTO.getNumeroCuenta(), filterDTO.getEstado(), filterDTO.getTipoCuenta(), pageable);
    }
}
