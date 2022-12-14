package com.middleware.colsubsidio.AgenciaEmpleo.model.repository;

import com.middleware.colsubsidio.AgenciaEmpleo.utils.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.middleware.colsubsidio.AgenciaEmpleo.model.InformacionVacanteDetalle;
import com.middleware.colsubsidio.AgenciaEmpleo.model.ResponseConversationalFlow;

@Slf4j
@Repository
public class SqlServerDao {

    @Qualifier("JdbcMaster")
    @Autowired()
    private JdbcTemplate jdbcTemplates;


    @Autowired
    private PropertiesUtil propertiesUtil;
    
    
   public List<ResponseConversationalFlow> findAllDetalleChatbotResponse (){
     List<ResponseConversationalFlow> resp = new ArrayList<>(0);
     
     try {
        resp = jdbcTemplates.queryForList(propertiesUtil.getQueryAllDetallesChatbot(), ResponseConversationalFlow.class);
        resp = resp.size() <= 0 ? null : resp;
     } catch (Exception e) {
        log.error(SqlServerDao.class.getName()+ " - findAllDetalleChatbotResponse"+ e.getMessage());
     } finally {
       try {
        DataSourceUtils.getConnection(jdbcTemplates.getDataSource()).close();
       } catch (SQLException sq) {
          log.error(SqlServerDao.class.getName() + "- Error! close conection" + sq.getMessage() + sq.getLocalizedMessage());
       }
     }
     return resp;
   }

public InformacionVacanteDetalle findByCelularInformacionDetalle (String celular){
    List<InformacionVacanteDetalle> result = new ArrayList<>(0);
    InformacionVacanteDetalle info = null;
  try {
     result = jdbcTemplates.query(propertiesUtil.getQueryDetalleByCelularInformacionVacante(), BeanPropertyRowMapper.newInstance(InformacionVacanteDetalle.class),celular);
     if(!result.isEmpty() && result != null){
       info = result.get(0);
     }  
  } catch (Exception e) {
    log.error(SqlServerDao.class.getName() + "- findByCelularInformacionDetalle" + e.getMessage());
  } finally{
     try {
        DataSourceUtils.getConnection(jdbcTemplates.getDataSource()).close();
     } catch (SQLException sq) {
        log.error(SqlServerDao.class.getName() + " - Error! close conection" + sq.getMessage() + sq.getLocalizedMessage());
     }
  }
  return info;
}


}