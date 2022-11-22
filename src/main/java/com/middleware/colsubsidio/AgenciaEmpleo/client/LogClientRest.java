package com.middleware.colsubsidio.AgenciaEmpleo.client;

import com.middleware.colsubsidio.AgenciaEmpleo.dto.MiddlewareRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.FooClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import feign.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "elasticsearch", url = "${log.endpoint.url}", configuration = FooClientConfig.class)
@Headers("Accept: application/json")
public interface LogClientRest {
    @PostMapping("${log.endpoint.log.method}")
    public ResponseEntity<?> sendLogtoKibana(@RequestBody MiddlewareRequest kibanaDTO, @RequestHeader(name = "Authorization") String authToken);


}
