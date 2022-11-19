package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import com.middleware.colsubsidio.AgenciaEmpleo.dto.AgendaCitaRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.InformacionHojaDeVidaRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.InformacionCursoRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.InformacionVacanteRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Attends;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Hsm;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Parameters;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.AgendamientoCita;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.DetalleSolicitud;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.RegistroCurso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class BuilderUtils {

    @Autowired
    Utils utils;

    @Autowired
    Parameters parameters;

    @Autowired
    PropertiesUtil propertiesUtil;


    public InformacionVacante registerBuilderInformacionVacante(InformacionHojaDeVidaRequest informacionHojaDeVidaRequest, DetalleSolicitud detalleSolicitud) {

        return InformacionVacante.builder().idCesante(informacionHojaDeVidaRequest.getInfoCesante().getCesanteId())
                .nombreCesante(informacionHojaDeVidaRequest.getInfoCesante().getNombre())
                .celularCesante(informacionHojaDeVidaRequest.getInfoCesante().getCelular())
                .nombreVacante(informacionHojaDeVidaRequest.getInfoVacante().getNombreVacante())
                .empresaVacante(informacionHojaDeVidaRequest.getInfoVacante().getEmpresaVacante())
                .detalleSolicitud(detalleSolicitud).build();
    }


    public DetalleSolicitud registerBuilderDetalle(int tipoSolicitud) {
        return DetalleSolicitud.builder()
                .fecha(new Date())
                .idTipoSolicitud(tipoSolicitud)
                .estado("p").build();
    }

    public RegistroCurso registerCursoBuilder(InformacionCursoRequest registroCursoRequest, DetalleSolicitud detalleSolicitud) {
        return RegistroCurso.builder()
                .curso(registroCursoRequest.getCurso().getNombre())
                .nombreCesante(registroCursoRequest.getInfoCesante().getNombre())
                .idCesante(registroCursoRequest.getInfoCesante().getCesanteId())
                .detalleSolicitud(detalleSolicitud).build();
    }

    public AgendamientoCita registerBuilderAgendamientoCita(AgendaCitaRequest agendaCitaRequest, DetalleSolicitud detalleSolicitud
    ){
        HandleDate  handleDate = new HandleDate();
      return AgendamientoCita.builder().idCesante(agendaCitaRequest.getInfoCesante().getCesanteId())
              .nombreCesante(agendaCitaRequest.getInfoCesante().getNombre())
              .celularCesante(agendaCitaRequest.getInfoCesante().getCelular())
              .nombreAgencia(agendaCitaRequest.getInfoAgenda().getAgencia().getNombre())
              .direccionAgencia(agendaCitaRequest.getInfoAgenda().getAgencia().getDireccion())
              .fecha(handleDate.retornFechaString(agendaCitaRequest.getInfoAgenda().getFecha().getDia(),
                      agendaCitaRequest.getInfoAgenda().getFecha().getMes(),agendaCitaRequest.getInfoAgenda().getFecha().getHora()))
              .detalleSolicitud(detalleSolicitud).build();
    }

    public InformacionVacante registerBuilderVacanteCompleta(InformacionVacanteRequest informacionVacanteRequest, DetalleSolicitud detalleSolicitud){
      return InformacionVacante.builder().idCesante(informacionVacanteRequest.getInfoCesante().getCesanteId())
              .nombreCesante(informacionVacanteRequest.getInfoCesante().getNombre())
              .celularCesante(informacionVacanteRequest.getInfoCesante().getCelular())
              .vacanteId(informacionVacanteRequest.getInfoVacante().getVacanteId())
              .nombreVacante(informacionVacanteRequest.getInfoVacante().getNombreVacante())
              .empresaVacante(informacionVacanteRequest.getInfoVacante().getEmpresaVacante())
              .salarioVacante(informacionVacanteRequest.getInfoVacante().getSalarioVacante())
              .horarioVacante(informacionVacanteRequest.getInfoVacante().getHorarioVacante())
              .ubicacionVacante(informacionVacanteRequest.getInfoVacante().getUbicacionVacante())
              .detalleSolicitud(detalleSolicitud)
              .build();
    }

    public Parameters mapPrepareParameters(DetalleSolicitud detalleSolicitud, int template) {
        if(template == 4){
            List<String> list = new ArrayList<>();
            list.add("XXXX-XXXXX-XXXX-1234");
            list.add("*"+detalleSolicitud.getInformacionVacante().getNombreVacante().toUpperCase()+"*");
            list.add("*"+detalleSolicitud.getInformacionVacante().getEmpresaVacante().toUpperCase()+"*");
            list.add(detalleSolicitud.getInformacionVacante().getNombreVacante());

            List<Hsm.Destination> list2 = new ArrayList<>();
            list2.add(Hsm.Destination.builder().destination(detalleSolicitud.getInformacionVacante().getCelularCesante()).build());

            parameters.setParameters(list);
            parameters.setAttends(Attends.builder().waitTime(5).build());
            parameters.setTemplate(propertiesUtil.getTemplateInformacionSeleccion().toString());
            parameters.setHsm(Hsm.builder().destinations(list2).build());
        }
      return parameters;
    }
}
