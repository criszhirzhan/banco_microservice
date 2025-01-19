package com.banco.banco_clientes.application.services;

import com.banco.banco_clientes.api.dto.ReporteDTO;
import com.banco.banco_clientes.api.filters.ReporteFilterDTO;
import org.springframework.data.domain.Page;

public interface ReporteService {
    public ReporteDTO getReporteWithFilter(ReporteFilterDTO filterDTO);
}
