package com.banco.banco_clientes.api.filters;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

@Data
@Getter
@Setter
public class ClienteFilterDTO {
    private String nombre;
    private String genero;
    private String estado;
    private String identificacion;
    private  int page = 0;
    private  int size = 10;

    public Pageable toPageable(){
        return PageRequest.of(page,size);
    }

}
