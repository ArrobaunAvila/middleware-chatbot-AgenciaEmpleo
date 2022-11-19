package com.middleware.colsubsidio.AgenciaEmpleo.enums;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public enum ErrorNum {

    TECHNICAL("001","Ocurri� un error inesperado",HttpStatus.INTERNAL_SERVER_ERROR),
    NO_ENTRY_DATA("000", "Please! validate the incoming data", HttpStatus.BAD_REQUEST),
    NO_SAVE_DOCUMENT("050","Warning Registro NO Save, Id Exist",HttpStatus.NOT_ACCEPTABLE),
	NO_EXIST_DOCUMENT("051","Warning Registro NO Exist",HttpStatus.NOT_ACCEPTABLE),
	NO_DELETE_DOCUMENT("053","Warning, Registro cannot be delete, Id No Exist",HttpStatus.NOT_ACCEPTABLE),
	NO_UPDATE_DOCUMENT("054","Warning, Registro cannot be update, Id No Exist",HttpStatus.NOT_ACCEPTABLE),
	NO_EXITS_DATA("055","Warning No exist date for processing",HttpStatus.NOT_ACCEPTABLE),
    NO_SEND_DATA("071", "Warning! Not send data HSM TipoSolicitud", HttpStatus.BAD_REQUEST);

    private String code;
    private String description;
    private HttpStatus httpCode;

}
