package com.middleware.colsubsidio.AgenciaEmpleo.dto;


import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionCesante;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InformacionCursoRequest {

    private InformacionCesante infoCesante;

    private Curso curso;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Curso{
        private String nombre;
    }
}
