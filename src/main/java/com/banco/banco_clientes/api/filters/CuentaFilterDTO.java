package com.banco.banco_clientes.api.filters;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class CuentaFilterDTO {
    private String cedula;
    private String numeroCuenta;
    private String tipoCuenta;
    private String estado;
    private  int page = 0;
    private  int size = 10;

    public Pageable toPageable(){
        return PageRequest.of(page,size);
    }
}
