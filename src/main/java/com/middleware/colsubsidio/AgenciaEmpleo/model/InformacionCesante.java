package com.middleware.colsubsidio.AgenciaEmpleo.model;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InformacionCesante {

    private String cesanteId;
    private String nombre;
    private String celular;
}
