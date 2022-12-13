package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Data
@Component
@Configuration
public class Database {

   @Autowired
   PropertiesUtil props;


   @Bean(name = "masterSqlServer")
   public DataSource buildDataSource(){
      return DataSourceBuilder.create().url(props.getUrl_database())
              .username(props.getUsername_database())
              .password(props.getPass_database())
              .build();
   }

   @Bean(name = "JdbcMaster")
   public JdbcTemplate jdbcTemplate(@Qualifier("masterSqlServer") DataSource dsMaster){
     return new JdbcTemplate(dsMaster);
   }

}
