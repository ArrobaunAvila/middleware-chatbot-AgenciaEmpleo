package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DetalleResponseChatbotDao {

    @Qualifier("JdbcMaster")
    @Autowired()
    private JdbcTemplate jdbcTemplates;


    @Autowired
    private PropertiesUtil propertiesUtil;
    

   
}
