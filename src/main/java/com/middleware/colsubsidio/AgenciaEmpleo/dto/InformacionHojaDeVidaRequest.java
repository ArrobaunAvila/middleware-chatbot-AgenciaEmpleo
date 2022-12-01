package com.middleware.colsubsidio.AgenciaEmpleo.dto;


import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionCesante;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InformacionHojaDeVidaRequest {

    private InformacionCesante infoCesante;

    private InformacionVacanteOptional infoVacante;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class InformacionVacanteOptional{
        private String nombreVacante;
        private String empresaVacante;
    }
}
