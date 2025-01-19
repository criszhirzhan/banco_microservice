package com.banco.banco_clientes.application.services.impl;


import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.filters.ClienteFilterDTO;
import com.banco.banco_clientes.application.exceptions.NotFoundEntityException;
import com.banco.banco_clientes.application.exceptions.RecordAlreadyExistsException;
import com.banco.banco_clientes.application.exceptions.RegistrationFailedException;
import com.banco.banco_clientes.application.services.ClienteService;
import com.banco.banco_clientes.application.utils.ConstantesApp;
import com.banco.banco_clientes.application.utils.Messages;
import com.banco.banco_clientes.persistence.entities.Cliente;
import com.banco.banco_clientes.persistence.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public String addCliente(ClienteDTO clienteDTO) {

        if(clienteRepository.findByIdentificacion(clienteDTO.getIdentificacion()).isPresent()){
            throw new RecordAlreadyExistsException(Messages.ALREADY_EXIST);
        }
        Cliente cliente = getCliente(clienteDTO);
        try {
            clienteRepository.save(cliente);
            return Messages.REGISTER_SUCCESSFUL;
        } catch (Exception e) {
            throw new RegistrationFailedException(Messages.REGISTER_FAILED);
        }
    }

    @Override
    public Page<ClienteDTO> getClientesWithFilter(ClienteFilterDTO filterDTO) {
        Pageable pageable = filterDTO.toPageable();
        return clienteRepository.findClientesWithFilter(filterDTO.getNombre(), filterDTO.getGenero(), filterDTO.getEstado(), filterDTO.getIdentificacion(), pageable);
    }

    @Override
    public String updateCliente(Long id, ClienteDTO clienteDTO) {


        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(Messages.NOT_FOUND));
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setClienteid(clienteDTO.getIdentificacion());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setEstado(clienteDTO.getEstado());
        try {
            clienteRepository.save(cliente);
            return Messages.UPDATE_SUCCESSFUL;
        } catch (Exception e) {
            throw new RegistrationFailedException(Messages.UPDATE_FAILED);
        }
    }

    @Override
    public String deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new NotFoundEntityException(Messages.NOT_FOUND));
        cliente.setEstado(ConstantesApp.INACTIVO);
        try {
            clienteRepository.save(cliente);
            return Messages.DELETE_SUCCESSFUL;
        } catch (Exception e) {
            throw new RegistrationFailedException(Messages.DELETE_FAILED);
        }
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
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setEstado(clienteDTO.getEstado());
        return cliente;
    }
}
