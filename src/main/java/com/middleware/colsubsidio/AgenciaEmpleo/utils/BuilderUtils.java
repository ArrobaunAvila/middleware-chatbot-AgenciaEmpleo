package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.*;
import com.middleware.colsubsidio.AgenciaEmpleo.model.*;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.*;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .estado(0)
                .idTipoSolicitud(tipoSolicitud)
                .build();
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
        Hsm hsm = new Hsm();
        List<String> listParameters = new ArrayList<>(0);
        List<Hsm.Destination> listDestination = new ArrayList<>(0);

        try {
            parameters.setId(detalleSolicitud.getId().toString());
            parameters.setDid(propertiesUtil.getParameterTemplateDid());
            parameters.setType(propertiesUtil.getParameterTemplateType());
            parameters.setChannel(propertiesUtil.getParameterTemplateChannel());

            hsm.setNamespace(propertiesUtil.getParameterTemplateNameSpace());
            hsm.setAttends(Attends.builder().waitTime(5).build());
            hsm.setLanguageCode(propertiesUtil.getParameterTemplateLanguagecode());
            hsm.setBotAttention(propertiesUtil.isParameterTemplateBotAttention());

            if (template == 4) {
                //Parameters Informacion hoja de vida PreSeleccionada
                listParameters = Arrays.asList(detalleSolicitud.getInformacionVacante().getNombreVacante(),
                        detalleSolicitud.getInformacionVacante().getNombreCesante(),
                        detalleSolicitud.getInformacionVacante().getEmpresaVacante(),
                        detalleSolicitud.getInformacionVacante().getNombreVacante());

                listDestination.add(Hsm.Destination.builder().destination(detalleSolicitud.getInformacionVacante().getCelularCesante()).build());
                hsm.setDestinations(listDestination);
                hsm.setParameters(listParameters);
                hsm.setTemplate(propertiesUtil.getTemplateInformacionSeleccion());
                parameters.setHsm(hsm);
            } else if(template == 3 ){
              //Parameters Informacion hoja de vida preselecionada2 --- mensaje 4 template

                listParameters = Arrays.asList(detalleSolicitud.getInformacionVacante().getNombreCesante(),
                        detalleSolicitud.getInformacionVacante().getEmpresaVacante(),
                        detalleSolicitud.getInformacionVacante().getNombreVacante());

                listDestination.add(Hsm.Destination.builder()
                        .destination(detalleSolicitud.getInformacionVacante().getCelularCesante().toString()).build());

                listDestination.add(Hsm.Destination.builder().destination(detalleSolicitud.getInformacionVacante().getCelularCesante()).build());
                hsm.setDestinations(listDestination);
                hsm.setParameters(listParameters);
                hsm.setTemplate(propertiesUtil.getTemplateInformacionPreseleccion4());
                parameters.setHsm(hsm);
            } else if(template == 2) {
                //Parameters Informacion Agendamiento cita
                gsonUtils = new GsonUtils();
               InformacionAgenda.Fecha obj =
                       gsonUtils.toObject(detalleSolicitud.getAgendamientoCita().getFecha(), InformacionAgenda.Fecha.class);

                listParameters = Arrays.asList(obj.getDia(),
                        handleDate.getMonthInPhraseFromInt(Integer.valueOf(obj.getMes())),
                        obj.getHora(),
                        detalleSolicitud.getAgendamientoCita().getNombreAgencia(),
                        detalleSolicitud.getAgendamientoCita().getDireccionAgencia());

               listDestination.add(Hsm.Destination.builder().destination(detalleSolicitud.getAgendamientoCita().getCelularCesante()).build());
                hsm.setDestinations(listDestination);
                hsm.setParameters(listParameters);
                hsm.setTemplate(propertiesUtil.getTemplatAgendamientoCita());
                parameters.setHsm(hsm);

            }else if(template == 1){
                //Parameters Informacion vacante proceso respuesta - intertes
                listParameters = Arrays.asList(detalleSolicitud.getInformacionVacante().getNombreCesante(),
                        "*"+detalleSolicitud.getInformacionVacante().getNombreVacante().concat("*"),
                        "*"+detalleSolicitud.getInformacionVacante().getEmpresaVacante().concat("*"),
                        detalleSolicitud.getInformacionVacante().getSalarioVacante(),
                        detalleSolicitud.getInformacionVacante().getHorarioVacante(),
                        detalleSolicitud.getInformacionVacante().getUbicacionVacante());

               listDestination.add(Hsm.Destination.builder().destination(detalleSolicitud.getInformacionVacante().getCelularCesante()).build());
                hsm.setDestinations(listDestination);
                hsm.setParameters(listParameters);
                hsm.setTemplate(propertiesUtil.getTemplateInformacionVacanteInteres());
                parameters.setHsm(hsm);
            }

        } catch (Exception e) {
            log.error("Error builder Parameters" + e.getMessage());
        }

        return parameters;
    }

    public DetalleResponseChatbot registerBuilderDetalleChatbot(ProcessChatbotRequest processChatbotRequest) throws JsonProcessingException {
        DetalleResponseChatbot detalleResponseChatbot = new DetalleResponseChatbot();
        detalleResponseChatbot.setCelular(processChatbotRequest.getUserDestination());
        detalleResponseChatbot.setInteres(processChatbotRequest.getOpcionUser().equals("Si") ? 1 : 0);
        detalleResponseChatbot.setContactar(processChatbotRequest.getOpcionUserContact().equals("Si") ? 1 : 0);
        detalleResponseChatbot.setResponseChatBot(utils.objetcMapperString(processChatbotRequest));
        detalleResponseChatbot.setFechaRegistro(new Date());
        return detalleResponseChatbot;
    }


    public ResponseDTO generateResponse200(HttpStatus httpStatus , String description){
        return ResponseDTO.builder().result(Result.builder().code(httpStatus.value())
                                .code_status(httpStatus.getReasonPhrase())
                                .description((description))
                                .build()).build();
    }

     public ResponseDTO generateResponse400(HttpStatus httpStatus , String description){
        return ResponseDTO.builder().result(Result.builder().code(httpStatus.value())
                                .code_status(httpStatus.getReasonPhrase())
                                .description((description))
                                .build()).build();
    }
}
