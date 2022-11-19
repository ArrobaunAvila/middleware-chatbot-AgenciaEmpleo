package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class Utils {

        @Autowired
    private PropertiesUtil propertiesUtil;

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

    public boolean validateObjects(List<Object> object) {
        boolean validated = true;
        for(Object obj: object){
            if(Objects.isNull(obj)){
              validated = false;
            }
        }
        return validated;
    }

}
