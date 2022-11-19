package com.middleware.colsubsidio.AgenciaEmpleo.services;


import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.AgendamientoCita;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.DetalleSolicitud;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.RegistroCurso;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.AgendaCitaRepository;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.DetalleRepository;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.InformacionRegistroCursoRepository;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.InformacionVacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicarService {

    private InformacionVacanteRepository informacionVacanteRepository;
    private DetalleRepository detalleRepository;

    private InformacionRegistroCursoRepository informacionRegistroCursoRepository;

    private AgendaCitaRepository agendaCitaRepository;

    @Autowired
    public PublicarService(InformacionVacanteRepository informacionVacanteRepository, DetalleRepository detalleRepository, InformacionRegistroCursoRepository informacionRegistroCursoRepository, AgendaCitaRepository agendaCitaRepository) {
        this.informacionVacanteRepository = informacionVacanteRepository;
        this.detalleRepository = detalleRepository;
        this.informacionRegistroCursoRepository = informacionRegistroCursoRepository;
        this.agendaCitaRepository = agendaCitaRepository;
    }

    public InformacionVacante guardarInformacion(InformacionVacante informacionVacante) {
        return this.informacionVacanteRepository.saveAndFlush(informacionVacante);
    }


    public DetalleSolicitud guardarDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        return this.detalleRepository.saveAndFlush(detalleSolicitud);
    }

    public RegistroCurso guardarInformacionRegistroCurso(RegistroCurso registroCurso) {
        return this.informacionRegistroCursoRepository.saveAndFlush(registroCurso);
    }

    public AgendamientoCita guardarInformacionAgendamientoCita(AgendamientoCita agendamientoCita){
       return this.agendaCitaRepository.saveAndFlush(agendamientoCita);
    }

    public InformacionVacante guardarInformacionVacante(InformacionVacante informacionVacante){
        return this.informacionVacanteRepository.saveAndFlush(informacionVacante);
    }
}
