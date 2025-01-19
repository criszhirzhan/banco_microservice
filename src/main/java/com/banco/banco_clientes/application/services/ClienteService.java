package com.banco.banco_clientes.application.services;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.filters.ClienteFilterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClienteService {

    public String addCliente(ClienteDTO clienteDTO);

    public Page<ClienteDTO> getClientesWithFilter(ClienteFilterDTO filterDTO);

    public String updateCliente(Long id, ClienteDTO clienteDTO);

    public String deleteCliente(Long id);
}
