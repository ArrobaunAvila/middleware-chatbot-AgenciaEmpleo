package com.middleware.colsubsidio.AgenciaEmpleo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAuthRequest implements Serializable {
	private static final long serialVersionUID = -2466635808657734196L;

	private String application;
	private String username;
	private String password;

}
