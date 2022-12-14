package com.middleware.colsubsidio.AgenciaEmpleo.server;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.SqlServerDao;


@Slf4j
@EnableAsync
@Service
public class ConsumerResponseService {
  
  @Autowired
  SqlServerDao sqlServerDao;


 
    public void consumerProcessResponseAgencyChatbot(){
      log.info(ConsumerResponseService.class.getName() +
       "Comenzando traza  cron consumerProcessResponseAgencyChatbot -->" + "Proceso envios Agencia Empleo");
       try {

        
       } catch (Exception e) {
        log.error(ConsumerResponseService.class.getName() + "- Error en method cron -> consumerProcessResponseAgencyChatbot : " +e.getMessage() );
       }
    }


    
}
