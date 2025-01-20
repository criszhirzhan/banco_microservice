package com.banco.banco_clientes.api.response;

import com.banco.banco_clientes.application.exceptions.ErrorDetail;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
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
