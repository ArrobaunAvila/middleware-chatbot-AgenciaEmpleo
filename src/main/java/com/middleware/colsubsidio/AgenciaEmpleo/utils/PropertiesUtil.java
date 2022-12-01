package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Getter
@PropertySource("file:config/general.properties")
public class PropertiesUtil {

    @Value("${api.hsm.url.login}")
    private String apiHsmLogin;

    @Value("${api.hsm.url.envio}")
    private String apiHsmEnvio;

    @Value("${api.hsm.consumo.user}")
    private String apiHsmUsuario;

    @Value("${api.hsm.consumo.pass}")
    private String apiHsmPassword;

    @Value("${api.access.token}")
    private String accessTokenTemporal;

    @Value("${formato.fecha.Agendamiento.cita}")
    private String validaFormatoFechaApp;

    @Value("${api.hsm.consumo.maxTimeInMillis}")
    private Long maxConsumerInMillisRequest;

    @Value("${rest.client.conn.max.idle.time}")
    private long maxIdleTime;

    @Value("${template.name.hsm.proceso.informacion.seleccion}")
    private String templateInformacionSeleccion;

    @Value("${template.nmae.hsm.proceso.register.curso}")
    private String templateRegisterCurso;

    @Value("${template.name.hsm.proceso.agendamiento.cita}")
    private String templatAgendamientoCita;

    @Value("${template.name.hsm.proceso.informacion.vacante.interes}")
    private String templateInformacionVacanteInteres;

    @Value("${template.name.hsm.proceso.informacion.preseleccion.message4}")
    private String templateInformacionPreseleccion4;

    @Value("${parameters.did}")
    private String parameterTemplateDid;

    @Value("${parameters.type}")
    private String parameterTemplateType;

    @Value("${parameters.channel}")
    private String parameterTemplateChannel;

    @Value("${parameters.languagecode}")
    private String parameterTemplateLanguagecode;

    @Value("${parameters.botAttention}")
    private boolean parameterTemplateBotAttention;

    @Value("${parameters.namespace}")
    private String parameterTemplateNameSpace;

     @Value("${token.logger}")
    private String tokenLogger;


     @Value("${kibana.log.index}")
    private String kibanaIndex;

    @Value("${kibana.log.type}")
    private String kibanaType;

    @Value("${kibana.log.side.registrar.informacion.preseleccion}")
    private String kibana_process_cv_preselection;

    @Value("${kibana.log.side.registar.informacion.vacante}")
    private String kibana_process_vacante_interes;

    @Value("${kibana.log.side.registrar.agendamiento}")
    private String kibana_agenda_cita;

    @Value("${kibana.log.side.registar.informacion.preseleccion.mensaje4}")
    private String kibana_preseleccion_mensaje4;

    @Value("${kibana.log.side.cron.envio}")
    private String process_cron;


}
