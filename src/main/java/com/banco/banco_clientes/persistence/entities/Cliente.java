package com.banco.banco_clientes.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Cliente extends Persona {

    @Column(unique = true)
    private String clienteid;

    private String password;
    private String estado;

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

}
