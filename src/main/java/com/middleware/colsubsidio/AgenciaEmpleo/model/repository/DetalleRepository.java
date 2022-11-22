package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.DetalleSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleSolicitud, Long> {

    @Query("SELECT d FROM DetalleSolicitud d where d.estado='p' and d.idTipoSolicitud <> 3")
    Optional<List<DetalleSolicitud>> getAllDetailsWithoutResponse();

    @Query("SELECT d FROM DetalleSolicitud d where d.estado='p' and d.idTipoSolicitud = 1")
    Optional<List<DetalleSolicitud>> getAllDetailWithAnswer();

}
