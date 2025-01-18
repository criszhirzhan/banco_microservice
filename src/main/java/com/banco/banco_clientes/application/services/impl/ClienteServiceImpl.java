package com.banco.banco_clientes.application.services.impl;


import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.filters.ClienteFilterDTO;
import com.banco.banco_clientes.application.exceptions.RecordAlreadyExistsException;
import com.banco.banco_clientes.application.exceptions.RegistrationFailedException;
import com.banco.banco_clientes.application.services.ClienteService;
import com.banco.banco_clientes.application.utils.Messages;
import com.banco.banco_clientes.persistence.entities.Cliente;
import com.banco.banco_clientes.persistence.repository.ClienteRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }


    @Override
    public String addCliente(ClienteDTO clienteDTO) {

        if(repository.findByIdentificacion(clienteDTO.getIdentificacion()).isPresent()){
            throw new RecordAlreadyExistsException(Messages.ALREADY_EXIST);
        }
        Cliente cliente = getCliente(clienteDTO);
        try {
            Cliente clienteSaved = repository.save(cliente);
            return Messages.REGISTER_SUCCESSFUL;
        } catch (Exception e) {
            throw new RegistrationFailedException(Messages.REGISTER_FAILED);
        }
    }

    @Override
    public Page<ClienteDTO> getClientesWithFilter(ClienteFilterDTO filterDTO) {
        Pageable pageable = filterDTO.toPageable();
        return repository.findClientesWithFilter(filterDTO.getNombre(), filterDTO.getGenero(), filterDTO.getEstado(), filterDTO.getIdentificacion(), pageable);
    }

    private static Cliente getCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setClienteid(clienteDTO.getIdentificacion());
        cliente.setContraseña(clienteDTO.getContraseña());
        cliente.setEstado(clienteDTO.getEstado());
        return cliente;
    }
}
