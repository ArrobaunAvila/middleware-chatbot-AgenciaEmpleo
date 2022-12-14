package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Utils {


    public String uuid(){
        return UUID.randomUUID().toString();
    }



    public String valorMapa(Map<String, String> params,String clave){
        if(params.containsKey(clave)){
           return params.get(clave);
        }else{
            return "";
        }
    }

    public boolean validateCriterios(String valor){
        if(valor==null || valor.isEmpty()){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }

    public boolean validaCriterioSoloLetras(String cadena){
        return cadena.matches("^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$");
    }

    public boolean validateObjects(List<Object> object) {
        boolean validated = true;
        for(Object obj: object){
            if(Objects.isNull(obj)){
              validated = false;
            }
        }
        return validated;
    }

    public String objetcMapperString(Object value) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        obj.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json = new ObjectMapper().writeValueAsString(value);
        log.info("Traza objetcMapperString() --> Json de envio" + json);
        return json;
    }
}
