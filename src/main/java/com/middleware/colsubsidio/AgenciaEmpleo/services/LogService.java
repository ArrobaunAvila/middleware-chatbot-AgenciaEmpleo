package com.middleware.colsubsidio.AgenciaEmpleo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.middleware.colsubsidio.AgenciaEmpleo.client.LogClientRest;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.MiddlewareRequest;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.HandleDate;
import com.middleware.colsubsidio.AgenciaEmpleo.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class LogService {

    @Autowired
	PropertiesUtil propertiesUtil;

    @Autowired
    HandleDate handleDate;

	private LogClientRest logClientRest;

    public LogService(LogClientRest logClientRest){
		this.logClientRest = logClientRest;
	}

	public String getAuthToken() {return "Bearer" + " " +this.propertiesUtil.getTokenLogger();}



     @Async
    public void sendLogToKibana(MiddlewareRequest appoinmentKibana, String transaction,String side) {
		try{
			appoinmentKibana.setIndex(propertiesUtil.getKibanaIndex());
		appoinmentKibana.setType(propertiesUtil.getKibanaType());
		appoinmentKibana.setSide(side);
		appoinmentKibana.setTransaction(transaction);
		appoinmentKibana.setMilliseconds(handleDate.getMillisecondsFromStartDate(appoinmentKibana.getStartdate()));
		logClientRest.sendLogtoKibana(appoinmentKibana, getAuthToken());
                }catch(Exception e){
                    e.printStackTrace();
                }
     }

     public void completeResponseService(MiddlewareRequest.Service service, boolean ok, Object response, boolean converResponseToJson) {
		service.setOk(ok);
		service.setTime(handleDate.getMillisecondsFromStartDate(service.getDateInitProcess()));
		if(response != null) {
			if(converResponseToJson) {
				try {
					service.setResponse(new ObjectMapper().writeValueAsString(response));
				} catch (JsonProcessingException e) {}
			} else {
				service.setResponse((String)response);
			}
		}
	}

	public MiddlewareRequest.Service initLogService(String name, String endPoint, Object request, boolean converResponseToJson) {
		MiddlewareRequest.Service service = new  MiddlewareRequest.Service();
		service.setName(name);
		service.setEndPoint(endPoint);
		if(request != null) {
			if(converResponseToJson) {
				try {
					service.setRequest(new ObjectMapper().writeValueAsString(request));
				} catch (Exception e) {}
			} else {
				service.setRequest((String) request);
			}
		}
		service.setDateInitProcess(new Date());
		return service;
	}


}
