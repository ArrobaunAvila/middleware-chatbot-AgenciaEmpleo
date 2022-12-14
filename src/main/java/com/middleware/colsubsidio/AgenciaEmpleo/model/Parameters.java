package com.middleware.colsubsidio.AgenciaEmpleo.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Parameters {

    private String id;

    private String did;

    private String type;

    private String channel;

    private Hsm hsm;


}
