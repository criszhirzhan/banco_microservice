package com.banco.banco_clientes.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generación de ID
    private Long id;

    @Column(unique = true) // Cada número de cuenta debe ser único
    private String numeroCuenta;

    private String tipoCuenta;
    private Double saldoInicial;
    private String estado;

    // Relación con Cliente
    @ManyToOne
    private Cliente cliente; // Una cuenta pertenece a un cliente

}

