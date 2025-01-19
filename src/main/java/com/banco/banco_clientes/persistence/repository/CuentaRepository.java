package com.banco.banco_clientes.persistence.repository;

import com.banco.banco_clientes.api.dto.ClienteDTO;
import com.banco.banco_clientes.api.dto.CuentaDTO;
import com.banco.banco_clientes.persistence.entities.Cliente;
import com.banco.banco_clientes.persistence.entities.Cuenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    @Query("SELECT new com.banco.banco_clientes.api.dto.CuentaDTO(c) FROM Cuenta c WHERE " +
            "( :cedula IS NULL OR c.cliente.identificacion LIKE %:cedula%) AND " +
            "( :numeroCuenta IS NULL OR c.numeroCuenta = :numeroCuenta ) AND " +
            "( :estado IS NULL OR c.estado = :estado ) AND " +
            "( :tipoCuenta IS NULL OR c.tipoCuenta = :tipoCuenta )")
    Page<CuentaDTO> findCuentasWithFilter(@Param("cedula") String cedula,
                                           @Param("numeroCuenta") String numeroCuenta,
                                           @Param("estado") String estado,
                                           @Param("tipoCuenta") String tipoCuenta,
                                           Pageable pageable);
}
