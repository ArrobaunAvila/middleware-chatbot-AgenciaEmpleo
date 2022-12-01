package com.middleware.colsubsidio.AgenciaEmpleo.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InformacionAgenda {

    @ApiModelProperty(hidden = false)
  private Fecha fecha;

    @ApiModelProperty(hidden = false)
  private Agencia agencia;
     @Builder
     @NoArgsConstructor
     @AllArgsConstructor
     @Getter
    public static class Fecha {
       private String dia;
       private String mes;
       private String hora;
    }
     @Builder
     @NoArgsConstructor
     @AllArgsConstructor
     @Getter
    public static class Agencia {
        private String nombre;
        private String direccion;
    }
}
