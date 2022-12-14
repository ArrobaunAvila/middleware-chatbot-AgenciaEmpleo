package com.middleware.colsubsidio.AgenciaEmpleo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build().apiInfo(this.getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "Middleware Chattigo AE(Agencia de Empleo)",
				"APi Middleware para flujos conversacionales Agencia de Empleo",
				"1.0",
				"",
				new Contact("Colsubsidio Agencia de empleo", "https://www.agenciadeempleocolsubsidio.com/agencia-movil/", "servicioalcliente@colsubsidio.com"),
				"",
				"",
				Collections.emptyList()
        );
    }
}
