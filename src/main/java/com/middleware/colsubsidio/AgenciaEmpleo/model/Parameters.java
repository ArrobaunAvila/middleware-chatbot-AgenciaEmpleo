package com.middleware.colsubsidio.AgenciaEmpleo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

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
