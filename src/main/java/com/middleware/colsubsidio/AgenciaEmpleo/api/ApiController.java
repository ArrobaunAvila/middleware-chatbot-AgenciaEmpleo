package com.middleware.colsubsidio.AgenciaEmpleo.api;

import com.middleware.colsubsidio.AgenciaEmpleo.business.ControllerBusiness;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/solicited")
public class ApiController {

    private ControllerBusiness controllerBussiness;

    public ApiController(ControllerBusiness controllerBusiness) {
        this.controllerBussiness = controllerBusiness;
    }


    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function() {
        return new ResponseEntity<>("Executed api controller solicitud", HttpStatus.OK);
    }

    @RequestMapping(value = "/processCvPreselection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> processCvPreselection(@RequestBody InformacionHojaDeVidaRequest informationVacantRequest) {
        return new ResponseEntity(this.controllerBussiness.procesoHojaDeVidaSeleccionada(informationVacantRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "/processRegisterCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> processRegisterCourse(@RequestBody InformacionCursoRequest informacionCursoRequest) {
        return new ResponseEntity(this.controllerBussiness.procesoRegistroACurso(informacionCursoRequest), HttpStatus.OK);
    }

    @RequestMapping(value= "/appointmentScheduling", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> appointmentScheduling(@RequestBody AgendaCitaRequest agendaCitaRequest){
        return new ResponseEntity<>(this.controllerBussiness.procesoRegistroAgenda(agendaCitaRequest),HttpStatus.OK);
    }

    @RequestMapping(value = "/processesVacancy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> processesVacancyInformation(@RequestBody InformacionVacanteRequest informacionVacanteRequest){
       return new ResponseEntity(this.controllerBussiness.procesoInformacionVacanteGestion(informacionVacanteRequest),HttpStatus.OK);
    }
}
