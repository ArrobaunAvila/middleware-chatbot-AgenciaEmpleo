package com.middleware.colsubsidio.AgenciaEmpleo;

import com.middleware.colsubsidio.AgenciaEmpleo.model.Parameters;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class MiddlewareChatbotAgenciaEmpleoApplication implements CommandLineRunner {

    @Autowired
    DetalleRepository detalleRepository;



    public static void main(String[] args) {
        SpringApplication.run(MiddlewareChatbotAgenciaEmpleoApplication.class, args);

    }


       @Override
    public void run(String... args) throws Exception {
        detalleRepository.getAllDetailsWithoutResponse()
                .get().forEach(s -> {
                    System.out.println(s.getId());
                });
    }
}
