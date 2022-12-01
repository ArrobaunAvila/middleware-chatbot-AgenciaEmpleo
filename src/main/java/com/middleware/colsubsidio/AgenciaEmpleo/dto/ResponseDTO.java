package com.middleware.colsubsidio.AgenciaEmpleo.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Result;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {

    @JsonProperty("resultado")
    private Result result;


    @JsonProperty("infoNoProcesada")
    private List<Object> objectsNoProcess;
}
