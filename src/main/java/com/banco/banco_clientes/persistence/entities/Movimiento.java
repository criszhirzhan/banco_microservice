package com.banco.banco_clientes.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private String tipoMovimiento;

    private String descripcionMovimiento;

    private Double valor;

    private Double saldo;

    @ManyToOne
    private Cuenta cuenta;
}
