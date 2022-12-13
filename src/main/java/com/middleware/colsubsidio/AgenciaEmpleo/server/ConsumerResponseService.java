package com.middleware.colsubsidio.AgenciaEmpleo.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Slf4j
@EnableAsync
@Service
public class ConsumerResponseService {


    public void consumerProcessResponseAgencyChatbot(){
      log.info(ConsumerResponseService.class.getName() + "Comenzando traza con cron consumerProcessResponseAgencyChatbot -->");
    }
}
