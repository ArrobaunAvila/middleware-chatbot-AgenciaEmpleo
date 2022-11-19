package com.middleware.colsubsidio.AgenciaEmpleo.model;

import lombok.*;

  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
public class InformacionVacante {

  private String vacanteId;
  private String nombreVacante;
  private String empresaVacante;
  private String salarioVacante;
  private String horarioVacante;
  private String ubicacionVacante;
}

