package com.middleware.colsubsidio.AgenciaEmpleo.services;


import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.*;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicarService {

    private InformacionVacanteRepository informacionVacanteRepository;
    private DetalleRepository detalleRepository;

    private InformacionRegistroCursoRepository informacionRegistroCursoRepository;

    private AgendaCitaRepository agendaCitaRepository;

    @Autowired
    private DetalleChatbotRepository detalleChatbotRepository;

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

    public DetalleResponseChatbot guardarResponseChatBot(DetalleResponseChatbot detalleResponseChatbot){
        return this.detalleChatbotRepository.saveAndFlush(detalleResponseChatbot);
    }
}
