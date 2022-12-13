package com.middleware.colsubsidio.AgenciaEmpleo.model.entity;


import javax.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "agendamiento_cita")
public class AgendamientoCita{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_Cesante")
    private String idCesante;

    @Column(name = "nombre_Cesante")
    private String nombreCesante;

    @Column(name= "celular_Cesante")
    private String celularCesante;

    @Column(name = "fecha_Agenda")
    private String fecha;

    @Column(name = "nombre_Agencia")
    private String nombreAgencia;

    @Column(name = "direccion_Agencia")
    private String direccionAgencia;

    @OneToOne()
    @JoinColumn(name = "id_Detalle")
    private DetalleSolicitud detalleSolicitud;


}
