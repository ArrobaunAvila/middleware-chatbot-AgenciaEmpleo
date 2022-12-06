package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.DetalleResponseChatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleChatbotRepository extends JpaRepository<DetalleResponseChatbot , Long > {

}
