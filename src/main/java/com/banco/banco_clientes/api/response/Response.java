package com.banco.banco_clientes.api.response;

import com.banco.banco_clientes.application.exceptions.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String response;
    private Integer code;
    private String message;
    private Object data;

    public Response(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public Response(String message){
        this.message=message;
    }


}
