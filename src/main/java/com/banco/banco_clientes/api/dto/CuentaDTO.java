package com.banco.banco_clientes.api.dto;

import com.banco.banco_clientes.persistence.entities.Cuenta;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CuentaDTO {
    private Long id;

    @NonNull
    private Long idCliente;

    @NotBlank(message = "El numero del cuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipoCuenta;

    @NonNull
    private Double saldoInicial;

    private String cliente;


    private String estado;

    private List<MovimientoDTO> movimientoDTOList;

    public CuentaDTO (Cuenta cuenta){
        this.id = cuenta.getId();
        this.idCliente = cuenta.getCliente().getId();
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.tipoCuenta = cuenta.getTipoCuenta();
        this.saldoInicial = cuenta.getSaldoInicial();
        this.cliente = cuenta.getCliente().getNombre();
        this.estado = cuenta.getEstado();
    }
}
