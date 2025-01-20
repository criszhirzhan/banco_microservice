package com.banco.banco_clientes.api.dto;

import com.banco.banco_clientes.persistence.entities.Cliente;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    private int edad;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;

    private String direccion;
    private String telefono;

    @NotBlank(message = "El cliente ID es obligatorio")
    private String clienteid;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private String estado;

    public ClienteDTO (Cliente cliente){
        this.clienteid = cliente.getClienteid();
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.edad = cliente.getEdad();
        this.identificacion = cliente.getIdentificacion();
        this.direccion = cliente.getDireccion();
        this.estado = cliente.getEstado();
        this.genero = cliente.getGenero();
        this.telefono = cliente.getTelefono();
        this.password = cliente.getPassword();
    }
}
