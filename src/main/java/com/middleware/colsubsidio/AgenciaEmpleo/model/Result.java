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

    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private String code_status;

    @JsonProperty("description")
    private String description;



}
