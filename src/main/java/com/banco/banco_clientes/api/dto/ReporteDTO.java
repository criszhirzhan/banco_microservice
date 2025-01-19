package com.banco.banco_clientes.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReporteDTO {

    private List<CuentaDTO> cuentas;
}
