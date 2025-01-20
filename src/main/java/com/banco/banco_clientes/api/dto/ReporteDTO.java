package com.banco.banco_clientes.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ReporteDTO {

    private List<CuentaDTO> cuentas;
}
