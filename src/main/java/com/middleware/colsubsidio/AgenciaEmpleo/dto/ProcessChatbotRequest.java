package com.middleware.colsubsidio.AgenciaEmpleo.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessChatbotRequest {

    private String opcionUser;
    private String opcionUserContact;
    private String userDestination;
}
