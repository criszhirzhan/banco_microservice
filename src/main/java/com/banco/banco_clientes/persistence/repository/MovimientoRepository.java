package com.banco.banco_clientes.persistence.repository;

import com.banco.banco_clientes.persistence.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
