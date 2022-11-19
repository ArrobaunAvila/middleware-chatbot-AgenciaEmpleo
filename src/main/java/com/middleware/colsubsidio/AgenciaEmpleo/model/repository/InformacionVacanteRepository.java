package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface InformacionVacanteRepository extends JpaRepository<InformacionVacante , Long> {

}
