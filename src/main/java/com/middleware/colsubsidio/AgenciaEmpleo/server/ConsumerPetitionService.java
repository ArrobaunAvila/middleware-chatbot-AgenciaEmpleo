package com.middleware.colsubsidio.AgenciaEmpleo.server;


import com.middleware.colsubsidio.AgenciaEmpleo.enums.ErrorNum;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Parameters;
import com.middleware.colsubsidio.AgenciaEmpleo.model.Request;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.DetalleRepository;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.BuilderUtils;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.PropertiesUtil;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@EnableAsync
public class ConsumerPetitionService {


    private PropertiesUtil propertiesUtil;

    private DetalleRepository detalleRepository;

    private BuilderUtils builderUtils;

    private String token;

    private LocalDateTime tokenDate;

    @Autowired
    Utils utils;

    public ConsumerPetitionService(PropertiesUtil propertiesUtil, DetalleRepository detalleRepository, BuilderUtils builderUtils) {
        this.propertiesUtil = propertiesUtil;
        this.detalleRepository = detalleRepository;
        this.builderUtils = builderUtils;
    }

    public void consumerProcessAgencyChatbot() {
        try {
            //this.getToken();
            this.processConsumerSendWithoutResponse();
        } catch (Exception e) {
            log.error("Error process getApi token", e.getMessage());
        }
    }

    private String getToken() throws Exception {if (this.token == null) {
            generateToken();
        } else {
            long tokenMillis = tokenDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long currMillis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long timeLive = currMillis - tokenMillis;
            if (timeLive > propertiesUtil.getMaxConsumerInMillisRequest()) {
                generateToken();
            }
        }
        return token;
    }

    private void generateToken() {
        token = consumerAccessToken(Request.builder()
                .userClient(propertiesUtil.getApiHsmUsuario())
                .passClient(propertiesUtil.getApiHsmPassword()).build());
        tokenDate = LocalDateTime.now();
    }


    private String consumerAccessToken(Request request) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(propertiesUtil.getApiHsmLogin());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Request> entity = new HttpEntity<Request>(request, headers);
        ResponseEntity<Request> result =
                restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, Request.class);

        return result.getBody().toString();
    }

    @Async
    private void processConsumerSendWithoutResponse() {
        try {
            detalleRepository.getAllDetailsWithoutResponse()
                    .get().stream().forEach(detail -> {
                        CompletableFuture<ResponseEntity> completableFuture = sendHttpRequestInformacion(
                                builderUtils.mapPrepareParameters(detail, detail.getIdTipoSolicitud()));
                        ResponseEntity response = completableFuture.join();

                        if (response.getStatusCode().is2xxSuccessful()) {
                            detail.setEstado("e");
                            detail.setFechaRespuesta(new Date());
                            detail.setRespuestaPeticion(response.toString());
                        } else {
                            detail.setFechaRespuesta(new Date());
                            detail.setRespuestaPeticion(response.toString());
                            log.info(ErrorNum.NO_SEND_DATA.getDescription().toString());
                        }
                      detalleRepository.save(detail);
            });

        } catch (Exception e) {
            log.error("Error process processConsumerEnvioInformacionVacante" + e.getMessage(), e.getMessage());
        }

    }

    @Async("asyncExecutor")
    private CompletableFuture<ResponseEntity> sendHttpRequestInformacion(Parameters parameters) {
        ResponseEntity result = null;
        try {
            String json = "";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(propertiesUtil.getApiHsmEnvio());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer" + propertiesUtil.getAccessTokenTemporal().toString());
            uri.queryParam("asincrono", "true");
            HttpEntity entity = new HttpEntity(utils.objetcMapperString(parameters), headers);
            result = restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, Object.class);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            log.error("Error process sendHttpRequestInformacion token", e.getMessage(), e.getCause().fillInStackTrace());
        }
        return CompletableFuture.completedFuture(result);
    }


}
