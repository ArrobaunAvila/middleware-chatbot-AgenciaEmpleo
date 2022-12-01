package com.middleware.colsubsidio.AgenciaEmpleo.dto;

import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionAgenda;
import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionCesante;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AgendaCitaRequest {
    private InformacionCesante infoCesante;

    private InformacionAgenda infoAgenda;


}
