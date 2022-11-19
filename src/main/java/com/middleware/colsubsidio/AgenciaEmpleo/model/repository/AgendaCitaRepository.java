package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.AgendamientoCita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaCitaRepository extends JpaRepository<AgendamientoCita, Long> {
}
