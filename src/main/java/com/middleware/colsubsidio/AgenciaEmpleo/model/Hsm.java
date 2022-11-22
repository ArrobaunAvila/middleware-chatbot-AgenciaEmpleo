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

    private List<Destination> destinations;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Destination {
        private String destination;
    }
}

