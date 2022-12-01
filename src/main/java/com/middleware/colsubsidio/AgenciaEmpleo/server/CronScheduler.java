package com.middleware.colsubsidio.AgenciaEmpleo.server;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {

   private  ConsumerPetitionService consumerPetitionService;

   public CronScheduler (ConsumerPetitionService consumerPetitionService){
   this.consumerPetitionService = consumerPetitionService;
   }

  @Scheduled(cron = "${cron.expression.send.minutes}")
  public void scheduleTaskProcessPetitionsAgencyEmpleo(){
       consumerPetitionService.consumerProcessAgencyChatbot();
  }


}
