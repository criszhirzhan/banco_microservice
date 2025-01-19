package com.banco.banco_clientes.application.services;

import com.banco.banco_clientes.api.dto.CuentaDTO;
import com.banco.banco_clientes.api.filters.CuentaFilterDTO;
import org.springframework.data.domain.Page;

public interface CuentaService {
    public String addCuenta(CuentaDTO cuentaDTO);

    public Page<CuentaDTO> getCuentasWithFilter(CuentaFilterDTO filterDTO);
}
