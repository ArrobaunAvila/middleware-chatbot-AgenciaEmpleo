package com.middleware.colsubsidio.AgenciaEmpleo.server;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {

   private  ConsumerPetitionService consumerPetitionService;

   private ConsumerResponseService consumerResponseService;

   public CronScheduler (ConsumerPetitionService consumerPetitionService, ConsumerResponseService consumerResponseService){
   this.consumerPetitionService = consumerPetitionService;
   this.consumerPetitionService = consumerPetitionService;
   }

  @Scheduled(cron = "${cron.expression.send.minutes}")
  public void scheduleTaskProcessPetitionsAgencyEmpleo(){
       consumerPetitionService.consumerProcessAgencyChatbot();
  }

  @Scheduled(cron = "${cron.expression.process.response.minutes}")
  public void scheduleTaskProcessResponseChatbot(){
      this.consumerResponseService.consumerProcessResponseAgencyChatbot();
  }

}
