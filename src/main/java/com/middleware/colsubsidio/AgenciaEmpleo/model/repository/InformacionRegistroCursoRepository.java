package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.RegistroCurso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacionRegistroCursoRepository extends JpaRepository<RegistroCurso, Long> {
}
