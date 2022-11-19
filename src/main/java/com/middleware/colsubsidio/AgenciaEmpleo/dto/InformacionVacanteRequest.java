package com.middleware.colsubsidio.AgenciaEmpleo.dto;
import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionCesante;
import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionVacante;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InformacionVacanteRequest {
  private InformacionCesante infoCesante;
  private InformacionVacante infoVacante;
}
