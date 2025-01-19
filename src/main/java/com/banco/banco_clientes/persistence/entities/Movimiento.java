package com.banco.banco_clientes.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    @ManyToOne
    private Cuenta cuenta;
}
