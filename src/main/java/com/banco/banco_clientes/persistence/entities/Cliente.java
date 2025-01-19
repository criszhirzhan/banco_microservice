package com.banco.banco_clientes.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cliente extends Persona {

    @Column(unique = true)
    private String clienteid;

    private String contraseña;
    private String estado;

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

}
