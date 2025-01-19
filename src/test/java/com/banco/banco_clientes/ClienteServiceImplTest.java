package com.banco.banco_clientes;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.filters.ClienteFilterDTO;
import com.banco.banco_clientes.application.exceptions.NotFoundEntityException;
import com.banco.banco_clientes.application.exceptions.RecordAlreadyExistsException;
import com.banco.banco_clientes.application.services.impl.ClienteServiceImpl;
import com.banco.banco_clientes.application.utils.Messages;
import com.banco.banco_clientes.persistence.entities.Cliente;
import com.banco.banco_clientes.persistence.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private ClienteDTO clienteDTO;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificacion("123456");
        clienteDTO.setNombre("Jose Lema");
        clienteDTO.setGenero("Masculino");
        clienteDTO.setEdad(30);
        clienteDTO.setDireccion("Otavalo sn y principal");
        clienteDTO.setTelefono("098254785");
        clienteDTO.setContraseña("1234");
        clienteDTO.setEstado("ACTIVO");

        cliente = new Cliente();
        cliente.setIdentificacion(clienteDTO.getIdentificacion());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setGenero(clienteDTO.getGenero());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setClienteid(clienteDTO.getIdentificacion());
        cliente.setContraseña(clienteDTO.getContraseña());
        cliente.setEstado(clienteDTO.getEstado());
    }

    @DisplayName("Dado que no existe un cliente con la identificación proporcionada, " +
            "esperamos que se registre correctamente un nuevo cliente.")
    @Test
    void testAddCliente_Exitoso() {
        when(clienteRepository.findByIdentificacion(clienteDTO.getIdentificacion())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        String resultado = clienteService.addCliente(clienteDTO);

        assertEquals(Messages.REGISTER_SUCCESSFUL, resultado);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @DisplayName("Dado que un cliente ya existe, cuando intentamos agregarlo, " +
            "entonces se debe lanzar una excepción 'RecordAlreadyExistsException' con el mensaje correspondiente")
    @Test
    void testAddCliente_YaExiste() {
        when(clienteRepository.findByIdentificacion(clienteDTO.getIdentificacion())).thenReturn(Optional.of(cliente));

        Exception exception = assertThrows(RecordAlreadyExistsException.class, () -> clienteService.addCliente(clienteDTO));

        assertEquals(Messages.ALREADY_EXIST, exception.getMessage());
    }

    @DisplayName("Dado que el cliente existe, cuando se actualiza, " +
            "entonces el cliente se actualiza correctamente y se debe devolver un mensaje de éxito")
    @Test
    void testUpdateCliente_Exitoso() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        String resultado = clienteService.updateCliente(id, clienteDTO);

        assertEquals(Messages.UPDATE_SUCCESSFUL, resultado);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @DisplayName("Dado que el cliente no existe, cuando intentamos actualizarlo, " +
            "entonces se debe lanzar una excepción 'NotFoundEntityException' con el mensaje correspondiente")
    @Test
    void testUpdateCliente_NoEncontrado() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundEntityException.class, () -> clienteService.updateCliente(id, clienteDTO));

        assertEquals(Messages.NOT_FOUND, exception.getMessage());
    }

    @DisplayName("Dado que el cliente existe, cuando se elimina, entonces el cliente se elimina correctamente y se debe devolver un mensaje de éxito")
    @Test
    void testDeleteCliente_Exitoso() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        String resultado = clienteService.deleteCliente(id);

        assertEquals(Messages.DELETE_SUCCESSFUL, resultado);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @DisplayName("Dado que el cliente no existe, cuando intentamos eliminarlo, " +
            "entonces se debe lanzar una excepción 'NotFoundEntityException' con el mensaje correspondiente")
    @Test
    void testDeleteCliente_NoEncontrado() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundEntityException.class, () -> clienteService.deleteCliente(id));

        assertEquals(Messages.NOT_FOUND, exception.getMessage());
    }

    @DisplayName("Dado un filtro de búsqueda, cuando consultamos clientes, entonces se debe devolver una lista de clientes que coincidan con el filtro aplicado")
    @Test
    void testGetClientesWithFilter() {
        ClienteFilterDTO filterDTO = new ClienteFilterDTO();
        filterDTO.setNombre("Jose");
        PageRequest pageable = PageRequest.of(0, 10);
        Page<ClienteDTO> page = new PageImpl<>(List.of(clienteDTO), pageable, 1);

        when(clienteRepository.findClientesWithFilter(any(), any(), any(), any(), any())).thenReturn(page);

        Page<ClienteDTO> resultado = clienteService.getClientesWithFilter(filterDTO);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
    }
}
