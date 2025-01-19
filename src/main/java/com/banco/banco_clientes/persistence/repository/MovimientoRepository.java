package com.banco.banco_clientes.persistence.repository;

import com.banco.banco_clientes.persistence.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.cliente.identificacion = :identificacion AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findMovimientosByIdentificacionAndFecha(@Param("identificacion") String identificacion,
                                                             @Param("fechaInicio") Date fechaInicio,
                                                             @Param("fechaFin") Date fechaFin);

}
