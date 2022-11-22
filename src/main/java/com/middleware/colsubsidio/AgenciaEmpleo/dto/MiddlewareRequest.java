package com.middleware.colsubsidio.AgenciaEmpleo.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class MiddlewareRequest {


    private String index;
    private String type;
    private String side;
    private String transaction;
    private Date startdate;
    private String messageText;
    private Boolean ok;
    private String error;
    private Long milliseconds;
    private List<Service> services;
    private Integer messages;
    private String userLogin;
    private String fechaHoraOperacion;


    @Data
    public static class Service implements Serializable {
        private static final long serialVersionUID = -1603769902670845023L;
        private String name;
        private String endPoint;
        private Date dateInitProcess;
        private Long time;
        private Boolean ok;
        private String request;
        private String response;
    }
}
