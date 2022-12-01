package com.middleware.colsubsidio.AgenciaEmpleo.model;

import com.middleware.colsubsidio.AgenciaEmpleo.utils.GsonUtils;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hsm {

    private String languageCode;

    private boolean botAttention;

    private String namespace;

    private String template;

    private List<String> parameters;

    private List<Destination> destinations;

    private Attends attends;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Destination {
        private String destination;
    }
}

