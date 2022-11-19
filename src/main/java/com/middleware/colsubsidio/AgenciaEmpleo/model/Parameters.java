package com.middleware.colsubsidio.AgenciaEmpleo.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Parameters {

    @Value("${parameters.id}")
    private String id;

    @Value("${parameters.did}")
    private String did;

    @Value("${parameters.type}")
    private String type;

    @Value("${parameters.channel}")
    private String channel;

    @Value("${parameters.languagecode}")
    private String languageCod;

    @Value("${parameters.botAttention}")
    private String botAttention;

    @Value("$parameters.namespace")
    private String namespace;

    private String template;

    private List<String> parameters;

    private Hsm hsm;

    private Attends attends;

}
