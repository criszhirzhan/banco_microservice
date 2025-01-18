package com.banco.banco_clientes.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cliente extends Persona {

    @Column(unique = true) // Cliente ID debe ser único
    private String clienteid;

    private String contraseña;
    private String estado;

    // Relación con las cuentas
    @OneToMany(mappedBy = "cliente") // Un cliente puede tener muchas cuentas
    private List<Cuenta> cuentas;

}
