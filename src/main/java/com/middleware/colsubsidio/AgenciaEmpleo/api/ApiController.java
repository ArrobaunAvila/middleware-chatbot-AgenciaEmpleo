package com.middleware.colsubsidio.AgenciaEmpleo.api;

import com.middleware.colsubsidio.AgenciaEmpleo.business.ControllerBusiness;
import com.middleware.colsubsidio.AgenciaEmpleo.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "*")
@RestController
public class ApiController {

    private ControllerBusiness controllerBussiness;

    public ApiController(ControllerBusiness controllerBusiness) {
        this.controllerBussiness = controllerBusiness;
    }


    @ApiOperation("valida la api en ejecucion!")
    @ApiResponses({
    	@ApiResponse(code=200, message = "OK")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<String> function() {
        return new ResponseEntity<>("Executed api sucessfull", HttpStatus.OK);
    }


    @ApiResponses({
    	@ApiResponse(code=200, message = "OK"),
         @ApiResponse(code = 400, message = "Bad Request!")
    })
    @ApiOperation("flujo conversacional hoja de vida preseleccionada")
    @RequestMapping(value = "/processcvpreselection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> processCvPreselection(@RequestBody InformacionHojaDeVidaRequest informationVacantRequest) {
        return new ResponseEntity<>(this.controllerBussiness.procesoHojaDeVidaSeleccionada(informationVacantRequest), HttpStatus.OK);
    }


    @ApiResponses({
    	@ApiResponse(code=200, message = "OK"),
         @ApiResponse(code = 400, message = "Bad Request!")
    })
    @ApiOperation(value = "flujo conversacional registro a curso", hidden = true)
    @RequestMapping(value = "/processregisterCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> processRegisterCourse(@RequestBody InformacionCursoRequest informacionCursoRequest) {
        return new ResponseEntity<>(this.controllerBussiness.procesoRegistroACurso(informacionCursoRequest), HttpStatus.OK);
    }


    @ApiResponses({
    	@ApiResponse(code=200, message = "OK"),
         @ApiResponse(code = 400, message = "Bad Request!")
    })
    @ApiOperation("flujo conversacional Agendamiento cita")
    @RequestMapping(value= "/appointmentscheduling", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> appointmentScheduling(@RequestBody AgendaCitaRequest agendaCitaRequest){
        return new ResponseEntity<>(this.controllerBussiness.procesoRegistroAgenda(agendaCitaRequest),HttpStatus.OK);
    }



    @ApiResponses({
    	@ApiResponse(code=200, message = "OK"),
         @ApiResponse(code = 400, message = "Bad Request!")
    })
    @ApiOperation("flujo conversacional Informacion de vacante ")
    @RequestMapping(value = "/processvacancy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseDTO> processVacancyInformation(@RequestBody ArrayList<InformacionVacanteRequest> informacionVacanteRequest){
       return new ResponseEntity<>(this.controllerBussiness.procesoInformacionVacanteGestion(informacionVacanteRequest),HttpStatus.OK);
    }

    @ApiResponses({
    	@ApiResponse(code=200, message = "OK"),
         @ApiResponse(code = 400, message = "Bad Request!")
    })
    @ApiOperation("flujo conversacional hoja de vida preseleccionada, mensaje 4")
   @PostMapping("/processpreselection")
   @ResponseBody
   public ResponseEntity<ResponseDTO> processcvmessage4(@RequestBody InformacionHojaDeVidaRequest informacionHojaDeVidaRequest){
     return new ResponseEntity<>(this.controllerBussiness.procesoPreselection(informacionHojaDeVidaRequest), HttpStatus.OK);
   }

   @ApiOperation(value = "flujo para registrar respuesta chatbot user" , hidden = true)
   @PostMapping("/processResponseChatbotRegister")
   @ResponseBody
   public ResponseEntity<?> processResponseChatbotRegister(@RequestBody ProcessChatbotRequest processChatbotRequest){
       return new ResponseEntity<>(this.controllerBussiness.procesoRegisterResponseUserChatBot(processChatbotRequest), HttpStatus.OK);
   }

}
