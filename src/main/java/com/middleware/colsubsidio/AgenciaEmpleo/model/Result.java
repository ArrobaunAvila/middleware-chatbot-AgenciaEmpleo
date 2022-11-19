package com.middleware.colsubsidio.AgenciaEmpleo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("descripcion")
    private String description;

}
