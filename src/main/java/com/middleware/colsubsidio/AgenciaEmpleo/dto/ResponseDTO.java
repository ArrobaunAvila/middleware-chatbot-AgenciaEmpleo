package com.middleware.colsubsidio.AgenciaEmpleo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {

    @JsonProperty("resultado")
    private Result result;
}
