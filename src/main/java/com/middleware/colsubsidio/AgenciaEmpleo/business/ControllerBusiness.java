package com.middleware.colsubsidio.AgenciaEmpleo.business;

import com.middleware.colsubsidio.AgenciaEmpleo.dto.*;
import com.middleware.colsubsidio.AgenciaEmpleo.enums.ErrorNum;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Result;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.RegistroCurso;
import com.middleware.colsubsidio.AgenciaEmpleo.services.LogService;
import com.middleware.colsubsidio.AgenciaEmpleo.services.PublicarService;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.BuilderUtils;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.HandleDate;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.PropertiesUtil;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.Arrays;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ControllerBusiness {

    @Autowired
    PublicarService publicarService;

    @Autowired
    LogService logService;

    @Autowired
    BuilderUtils builderUtils;

    @Autowired
    Utils utils;

    @Autowired
    HandleDate handleDate;

    @Autowired
    PropertiesUtil proper;

    private ResponseDTO responseDTO = null;

    public ResponseDTO procesoHojaDeVidaSeleccionada(InformacionHojaDeVidaRequest informacionVacanteRequest){

        try {
             List<MiddlewareRequest.Service> list = new ArrayList<>(0);

             MiddlewareRequest middlewareRequest = new MiddlewareRequest();
                     middlewareRequest.setStartdate(new Date());
                     middlewareRequest.setFechaHoraOperacion(handleDate.dateToString(new Date()));
                     middlewareRequest.setMessageText("Comenzando traza service processesVacancyInformation--->");

            MiddlewareRequest.Service service =  logService.initLogService(proper.getProcess_cv_preselection(),"processCvPreselection",
                     informacionVacanteRequest, true);
            list.add(service);
            middlewareRequest.setServices(list);
            if (Objects.isNull(informacionVacanteRequest.getInfoVacante()) || Objects.isNull(informacionVacanteRequest.getInfoCesante())
                    || !utils.validateCriterios(informacionVacanteRequest.getInfoCesante().getCesanteId())
                    || !utils.validateCriterios(informacionVacanteRequest.getInfoCesante().getCelular())
                    || !utils.validateCriterios(informacionVacanteRequest.getInfoCesante().getNombre())
                    || !utils.validateCriterios(informacionVacanteRequest.getInfoVacante().getEmpresaVacante())
                    || !utils.validateCriterios(informacionVacanteRequest.getInfoVacante().getNombreVacante())) {

                logService.completeResponseService(service,
                        false,  "", true, "Error! Datos entry invalidos!");
                this.responseDTO = ResponseDTO.builder()
                        .result(Result.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .code_status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .description((ErrorNum.NO_ENTRY_DATA.getDescription()))
                                .build()).build();
            } else {

                try {

                 InformacionVacante info =  publicarService.guardarInformacion(builderUtils.registerBuilderInformacionVacante(informacionVacanteRequest,
                            publicarService.guardarDetalleSolicitud(builderUtils.registerBuilderDetalle(4))));

                    logService.completeResponseService(service , true, info , true, "Ok! Registros Insertados correctamente");

                    this.responseDTO = ResponseDTO.builder()
                            .result(Result.builder().code(HttpStatus.OK.value())
                                    .code_status(HttpStatus.OK.getReasonPhrase())
                                    .description("Exito! proceso realizado")
                                    .build()).build();

                } catch (Exception e) {
                    logService.completeResponseService(service , false, "" , true, e.getMessage());
                    log.error("Error! Excepcion insertando solicitud Informacion Vacante a curso--->" + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
                }
                logService.sendLogToKibana(middlewareRequest, true, proper.getProcess_cv_preselection());
            }
        } catch (Exception e) {
            log.error("Excepcion al realizar procesoHojaDeVidaSeleccionada  -> " + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
        }
        return this.responseDTO;
    }

    public ResponseDTO procesoRegistroACurso(InformacionCursoRequest informacionCursoRequest) {

        try {
            if (Objects.isNull(informacionCursoRequest.getCurso()) || Objects.isNull(informacionCursoRequest.getInfoCesante())
                    || !utils.validateCriterios(informacionCursoRequest.getCurso().getNombre())
                    || !utils.validateCriterios(informacionCursoRequest.getInfoCesante().getCesanteId())
                    || !utils.validateCriterios(informacionCursoRequest.getInfoCesante().getNombre())
                    || !utils.validateCriterios(informacionCursoRequest.getInfoCesante().getCelular())) {

                this.responseDTO = ResponseDTO.builder()
                        .result(Result.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .code_status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .description((ErrorNum.NO_ENTRY_DATA.getDescription()))
                                .build()).build();

                return this.responseDTO;
            }

                try {
                RegistroCurso info = publicarService.guardarInformacionRegistroCurso(
                        builderUtils.registerCursoBuilder(informacionCursoRequest,
                                publicarService.guardarDetalleSolicitud(builderUtils.registerBuilderDetalle(3))));

                if (Objects.nonNull(info)) {
                     this.responseDTO = ResponseDTO.builder()
                            .result(Result.builder().code(HttpStatus.OK.value())
                                    .code_status(HttpStatus.OK.getReasonPhrase())
                                    .description("Exito! proceso realizado")
                                    .build()).build();
                }
            } catch (Exception e) {
                log.error("Error! Excepcion insertando solicitud registro a curso--->" + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
            }


        } catch (Exception e) {
            log.error("Excepcion al realizar procesoRegistroACurso  -> " + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
        }
        return this.responseDTO;
    }

    public ResponseDTO procesoRegistroAgenda(AgendaCitaRequest agendaCitaRequest){

        try {
            if(Objects.isNull(agendaCitaRequest.getInfoAgenda()) || Objects.isNull(agendaCitaRequest.getInfoCesante())
                || !utils.validateCriterios(agendaCitaRequest.getInfoCesante().getCesanteId())
                || !utils.validateCriterios(agendaCitaRequest.getInfoCesante().getCelular())
                || !utils.validateCriterios(agendaCitaRequest.getInfoCesante().getNombre())
                || !utils.validateCriterios(agendaCitaRequest.getInfoAgenda().getAgencia().getDireccion())
                || !utils.validateCriterios(agendaCitaRequest.getInfoAgenda().getAgencia().getNombre())
                || !utils.validateCriterios(agendaCitaRequest.getInfoAgenda().getFecha().getDia())){

                this.responseDTO = ResponseDTO.builder()
                        .result(Result.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .code_status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .description((ErrorNum.NO_ENTRY_DATA.getDescription()))
                                .build()).build();

                return this.responseDTO;
            }

            try {
                publicarService.guardarInformacionAgendamientoCita(builderUtils.registerBuilderAgendamientoCita(agendaCitaRequest,
                        publicarService.guardarDetalleSolicitud(builderUtils.registerBuilderDetalle(2))));

                     this.responseDTO = ResponseDTO.builder()
                            .result(Result.builder().code(HttpStatus.OK.value())
                                    .code_status(HttpStatus.OK.getReasonPhrase())
                                    .description("Exito! proceso realizado")
                                    .build()).build();
            }catch (Exception e){
                log.error("Error! Excepcion insertando solicitud Agenga cita --->" + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
            }

        }catch (Exception e){
            log.error("Excepcion al realizar proceso RegistroAgendar Cita  -> " + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
        }
     return this.responseDTO;
    }

    public ResponseDTO procesoInformacionVacanteGestion(ArrayList<InformacionVacanteRequest> informacionVacanteRequest) {

        try {
            List<Object> objectInvalidos = new ArrayList<>();
            if (informacionVacanteRequest.size() > 0) {
               informacionVacanteRequest.stream().filter(info -> Objects.nonNull(info))
                       .forEach(obj -> {
                            try {
                                if (Objects.isNull(obj.getInfoVacante()) || Objects.isNull(obj.getInfoCesante())
                                        || !utils.validateCriterios(obj.getInfoCesante().getCesanteId())
                                        || !utils.validateCriterios(obj.getInfoCesante().getCelular())
                                        || !utils.validateCriterios(obj.getInfoCesante().getNombre())
                                        || !utils.validateCriterios(obj.getInfoVacante().getEmpresaVacante())
                                        || !utils.validateCriterios(obj.getInfoVacante().getNombreVacante())) {
                                         objectInvalidos.add(obj);
                                } else {
                                publicarService.guardarInformacionVacante(builderUtils.registerBuilderVacanteCompleta(obj,
                                        publicarService.guardarDetalleSolicitud(builderUtils.registerBuilderDetalle(1))));
                                }
                            } catch (Exception e) {
                                log.error("Error! Excepcion insertando Informacion Vacante Gestion  --->" + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());

                            }
                       });
                responseDTO = ResponseDTO.builder().objectsNoProcess(objectInvalidos)
                        .result(Result.builder().code(HttpStatus.OK.value())
                                .code_status(HttpStatus.OK.getReasonPhrase())
                                .description("Exito! proceso realizado")
                                .build()).build();

            } else {
                responseDTO = ResponseDTO.builder()
                        .result(Result.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .code_status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .description((ErrorNum.NO_ENTRY_DATA.getDescription()))
                                .build()).build();

            }
        } catch (Exception e) {
            log.error("Excepcion al realizar proceso Informacion Vacante Gestion  -> " + ErrorNum.TECHNICAL.getDescription(), e.fillInStackTrace().getCause());
        }

        return responseDTO;
    }
}
