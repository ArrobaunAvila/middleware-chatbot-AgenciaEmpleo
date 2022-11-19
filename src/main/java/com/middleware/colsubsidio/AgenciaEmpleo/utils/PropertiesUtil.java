package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Getter
public class PropertiesUtil {

    @Value("${api.hsm.url.login}")
    private String apiHsmLogin;

    @Value("${api.hsm.url.envio}")
    private String apiHsmEnvio;

    @Value("${api.hsm.consumo.user}")
    private String apiHsmUsuario;

    @Value("${api.hsm.consumo.pass}")
    private String apiHsmPassword;

    @Value("${template.name.hsm.proceso.informacion.seleccion}")
    private String templateInformacionSeleccion;

    @Value("${template.nmae.hsm.proceso.register.curso}")
    private String templateRegisterCurso;

    @Value("${template.name.hsm.proceso.agendamiento.cita}")
    private String templatAgendamientoCita;

    @Value("${template.name.hsm.proceso.informacion.vacante.interes}")
    private String templateInformacionVacanteInteres;

    @Value("${formato.fecha.Agendamiento.cita}")
    private String validaFormatoFechaApp;

    @Value("${api.hsm.consumo.maxTimeInMillis}")
    private Long maxConsumerInMillisRequest;

    @Value("${id.template.hsm.agencia.procesoCv}")
    private int procesoSeleccionHojaVida;


}
