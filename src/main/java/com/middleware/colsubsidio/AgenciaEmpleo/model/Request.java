package com.middleware.colsubsidio.AgenciaEmpleo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Request {

    @JsonProperty("username")
    private String userClient;

    @JsonProperty("password")
    private String passClient;

    @JsonProperty("access_token")
    private String accessToken;
}
