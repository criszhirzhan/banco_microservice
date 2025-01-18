package com.banco.banco_clientes.persistence.repository;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.persistence.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String identificacion);

    @Query("SELECT new com.banco.banco_clientes.api.dto.ClienteDTO(c) FROM Cliente c WHERE " +
            "( :nombre IS NULL OR c.nombre LIKE %:nombre%) AND " +
            "( :genero IS NULL OR c.genero = :genero ) AND " +
            "( :estado IS NULL OR c.estado = :estado ) AND " +
            "( :identificacion IS NULL OR c.identificacion = :identificacion )")
    Page<ClienteDTO> findClientesWithFilter(@Param("nombre") String nombre,
                                            @Param("genero") String genero,
                                            @Param("estado") String estado,
                                            @Param("identificacion") String identificacion,
                                            Pageable pageable);
}
