package com.banco.banco_clientes.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MovimientoDTO {

    private long id;

    @NonNull
    private Long idCuenta;

    private String tipoMovimiento;

    private String descripcionMovimiento;

    private String fecha;

    private Double saldo;

    @NonNull
    private Double valor;

}
