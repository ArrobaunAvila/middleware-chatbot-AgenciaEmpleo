package com.middleware.colsubsidio.AgenciaEmpleo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter; 

@Builder
@AllArgsConstructor
@Getter
public class InformacionVacanteDetalle {
  

  private String idDetalle;

  private String idCesante;

  private String celularCesante;

  private String nombreCesante;

  private String idVacante;

  private String nombreVacante;

    
}
