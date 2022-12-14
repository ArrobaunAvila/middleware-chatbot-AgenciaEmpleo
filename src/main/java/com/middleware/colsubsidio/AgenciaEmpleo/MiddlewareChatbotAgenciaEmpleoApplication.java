package com.middleware.colsubsidio.AgenciaEmpleo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc 
@EnableScheduling
@CrossOrigin(origins = "*")
public class MiddlewareChatbotAgenciaEmpleoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareChatbotAgenciaEmpleoApplication.class, args);

    }
}
