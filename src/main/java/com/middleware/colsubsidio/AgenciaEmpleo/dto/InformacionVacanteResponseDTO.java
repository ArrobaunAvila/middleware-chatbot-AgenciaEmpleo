package com.middleware.colsubsidio.AgenciaEmpleo.dto;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InformacionVacanteResponseDTO {

    private String cesanteId;

    private String vacanteId;

    private boolean respuesta;

}
