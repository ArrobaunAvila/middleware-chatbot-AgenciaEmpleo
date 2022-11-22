package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import com.google.gson.Gson;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.AgendaCitaRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.InformacionHojaDeVidaRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.InformacionCursoRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.InformacionVacanteRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Attends;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Hsm;
import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionAgenda;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Parameters;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.AgendamientoCita;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.DetalleSolicitud;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.RegistroCurso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class BuilderUtils {

    @Autowired
    Utils utils;

    @Autowired
    PropertiesUtil propertiesUtil;

    @Autowired
    HandleDate handleDate;

    private GsonUtils gsonUtils;
    public InformacionVacante registerBuilderInformacionVacante(InformacionHojaDeVidaRequest informacionHojaDeVidaRequest, DetalleSolicitud detalleSolicitud) {

        return InformacionVacante.builder().idCesante(informacionHojaDeVidaRequest.getInfoCesante().getCesanteId())
                .nombreCesante(informacionHojaDeVidaRequest.getInfoCesante().getNombre())
                .celularCesante("57"+informacionHojaDeVidaRequest.getInfoCesante().getCelular())
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
                .celular("57"+registroCursoRequest.getInfoCesante().getCelular())
                .idCesante(registroCursoRequest.getInfoCesante().getCesanteId())
                .detalleSolicitud(detalleSolicitud).build();
    }

    public AgendamientoCita registerBuilderAgendamientoCita(AgendaCitaRequest agendaCitaRequest, DetalleSolicitud detalleSolicitud
    ) {
        return AgendamientoCita.builder().idCesante(agendaCitaRequest.getInfoCesante().getCesanteId())
                .nombreCesante(agendaCitaRequest.getInfoCesante().getNombre())
                .celularCesante("57"+agendaCitaRequest.getInfoCesante().getCelular())
                .nombreAgencia(agendaCitaRequest.getInfoAgenda().getAgencia().getNombre())
                .direccionAgencia(agendaCitaRequest.getInfoAgenda().getAgencia().getDireccion())
                .fecha(handleDate.retornFechaString(agendaCitaRequest.getInfoAgenda().getFecha()))
                .detalleSolicitud(detalleSolicitud).build();
    }

    public InformacionVacante registerBuilderVacanteCompleta(InformacionVacanteRequest informacionVacanteRequest, DetalleSolicitud detalleSolicitud) {
        return InformacionVacante.builder().idCesante(informacionVacanteRequest.getInfoCesante().getCesanteId())
                .nombreCesante(informacionVacanteRequest.getInfoCesante().getNombre())
                .celularCesante("57"+informacionVacanteRequest.getInfoCesante().getCelular())
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

        Parameters parameters = new Parameters();
        List<String> listParameters = new ArrayList<>(0);
        List<Hsm.Destination> listDestionation = new ArrayList<>(0);

        try {
            parameters.setDid(propertiesUtil.getParameterTemplateDid());
            parameters.setType(propertiesUtil.getParameterTemplateType());
            parameters.setChannel(propertiesUtil.getParameterTemplateChannel());
            parameters.setNamespace(propertiesUtil.getParameterTemplateNameSpace());
            parameters.setLanguageCod(propertiesUtil.getParameterTemplateLanguagecode());
            parameters.setBotAttention(propertiesUtil.isParameterTemplateBotAttention());
            parameters.setAttends(Attends.builder().waitTime(5).build());

            if (template == 4) {
                //Parameters Informacion hoja de vida PreSeleccionada
                listParameters = Arrays.asList(detalleSolicitud.getInformacionVacante().getNombreVacante(),
                        detalleSolicitud.getInformacionVacante().getNombreCesante(),
                        detalleSolicitud.getInformacionVacante().getEmpresaVacante());

                listDestionation.add(Hsm.Destination.builder()
                        .destination(detalleSolicitud.getInformacionVacante()
                                .getCelularCesante()).build());

                parameters.setId(detalleSolicitud.getId().toString());
                parameters.setHsm(Hsm.builder().destinations(listDestionation).build());
                parameters.setTemplate(propertiesUtil.getTemplateInformacionSeleccion());
                parameters.setParameters(listParameters);
            } else if(template == 3 ){
              //Parameters Informacion Registro curso
                listParameters = Arrays.asList(detalleSolicitud.getRegistroCurso().getCurso());

                listDestionation.add(Hsm.Destination.builder()
                        .destination(detalleSolicitud.getRegistroCurso().getCelular().toString()).build());

                parameters.setId(detalleSolicitud.getId().toString());
                parameters.setHsm(Hsm.builder().destinations(listDestionation).build());
                parameters.setTemplate(propertiesUtil.getTemplateRegisterCurso());
                parameters.setParameters(listParameters);
            } else if(template == 2) {
                //Parameters Informacion Agendamiento cita
                gsonUtils = new GsonUtils();
               InformacionAgenda.Fecha obj =
                       gsonUtils.toObject(detalleSolicitud.getAgendamientoCita().getFecha(), InformacionAgenda.Fecha.class);

                listParameters = Arrays.asList(obj.getDia(),
                        obj.getHora(),
                        obj.getMes(),
                        detalleSolicitud.getAgendamientoCita().getNombreAgencia(),
                        detalleSolicitud.getAgendamientoCita().getDireccionAgencia());

                listDestionation.add(Hsm.Destination.builder()
                        .destination(detalleSolicitud.getAgendamientoCita().getCelularCesante()).build());

                parameters.setId(detalleSolicitud.getId().toString());
                parameters.setHsm(Hsm.builder().destinations(listDestionation).build());
                parameters.setTemplate(propertiesUtil.getTemplatAgendamientoCita());
                parameters.setParameters(listParameters);

            }else if(template == 1){

                listParameters = Arrays.asList(detalleSolicitud.getInformacionVacante().getNombreVacante(),
                        detalleSolicitud.getInformacionVacante().getEmpresaVacante(),
                        detalleSolicitud.getInformacionVacante().getSalarioVacante(),
                        detalleSolicitud.getInformacionVacante().getHorarioVacante(),
                        detalleSolicitud.getInformacionVacante().getUbicacionVacante());

                listDestionation.add(Hsm.Destination.builder()
                        .destination(detalleSolicitud.getInformacionVacante().getCelularCesante()).build());

                parameters.setId(detalleSolicitud.getId().toString());
                parameters.setHsm(Hsm.builder().destinations(listDestionation).build());
                parameters.setTemplate(propertiesUtil.getTemplateInformacionVacanteInteres());
                parameters.setParameters(listParameters);
            }

        } catch (Exception e) {
            log.error("Error builder Parameters" + e.getMessage());
        }

        return parameters;
    }
}
