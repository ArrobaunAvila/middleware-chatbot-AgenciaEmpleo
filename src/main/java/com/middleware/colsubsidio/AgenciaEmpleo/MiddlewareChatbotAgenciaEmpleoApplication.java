package com.middleware.colsubsidio.AgenciaEmpleo;

import com.middleware.colsubsidio.AgenciaEmpleo.model.Parameters;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@CrossOrigin(origins = "*")
public class MiddlewareChatbotAgenciaEmpleoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareChatbotAgenciaEmpleoApplication.class, args);

    }
}
