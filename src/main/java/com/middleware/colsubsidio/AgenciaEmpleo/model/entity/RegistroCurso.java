package com.middleware.colsubsidio.AgenciaEmpleo.model.entity;


import javax.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registro_curso")
public class RegistroCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_Cesante")
    private String idCesante;

    @Column(name = "nombre_Cesante")
    private String nombreCesante;

    private String curso;

    @OneToOne()
    @JoinColumn(name = "id_Detalle")
    private DetalleSolicitud detalleSolicitud;

}
