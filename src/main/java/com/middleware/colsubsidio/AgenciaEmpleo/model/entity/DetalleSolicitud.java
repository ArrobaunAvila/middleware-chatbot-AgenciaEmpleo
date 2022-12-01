package com.middleware.colsubsidio.AgenciaEmpleo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Setter
@Table(name = "detalle_solicitud")
public class DetalleSolicitud {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro",nullable = true)
    private Date fecha;

    @Column(name = "estado_peticion")
    private Integer estado;

    @Column(name = "respuesta_peticion", nullable = true)
    private String respuestaPeticion;

    @Column(name = "fecha_respuesta", nullable = true)
    private Date fechaRespuesta;

    @OneToOne(mappedBy = "detalleSolicitud", cascade = CascadeType.ALL)
    private InformacionVacante informacionVacante;

    @OneToOne(mappedBy = "detalleSolicitud",cascade = CascadeType.ALL)
    private RegistroCurso registroCurso;

    @OneToOne(mappedBy = "detalleSolicitud", cascade = CascadeType.ALL)
    private AgendamientoCita agendamientoCita;


}
