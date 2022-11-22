package com.middleware.colsubsidio.AgenciaEmpleo.services;

import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.DetalleSolicitud;
import com.middleware.colsubsidio.AgenciaEmpleo.model.entity.InformacionVacante;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.DetalleRepository;
import com.middleware.colsubsidio.AgenciaEmpleo.model.repository.InformacionVacanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PublicarServiceTest {

    @Mock
    DetalleRepository detalleRepository;

    @Mock
    InformacionVacanteRepository informacionVacanteRepository;

    @InjectMocks
    PublicarService publicarService;

    @Mock
    private InformacionVacante informacionVacante;


    private DetalleSolicitud detalleSolicitud;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        detalleSolicitud = Mockito.mock(DetalleSolicitud.class);
        detalleSolicitud.setEstado("p");
        detalleSolicitud.setIdTipoSolicitud(4);
        detalleSolicitud.setFecha(new Date());


         informacionVacante = new InformacionVacante();
         informacionVacante.setNombreVacante("Desarrollo");
         informacionVacante.setCelularCesante("302546434");
         informacionVacante.setVacanteId("12312");
         informacionVacante.setEmpresaVacante("colsubsidio");
         informacionVacante.setNombreCesante("Daniel avila");
         informacionVacante.setDetalleSolicitud(detalleSolicitud);
    }

    @Test
    void guardarInformacion() {
        Mockito.when(informacionVacanteRepository.saveAndFlush(Mockito.any(InformacionVacante.class))).thenReturn(informacionVacante);
        assertNotNull(publicarService.guardarInformacion(informacionVacante));
    }

    @Test
    void guardarDetalleSolicitud() {
      Mockito.when(detalleRepository.saveAndFlush(Mockito.any(DetalleSolicitud.class))).thenReturn(detalleSolicitud);
      assertNotNull(publicarService.guardarDetalleSolicitud(new DetalleSolicitud()));
    }

    @Test
    void guardarInformacionRegistroCurso() {
    }

    @Test
    void guardarInformacionAgendamientoCita() {
    }

    @Test
    void guardarInformacionVacante() {
    }
}