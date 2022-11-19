package com.middleware.colsubsidio.AgenciaEmpleo.model.entity;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "informacion_vacante")
public class InformacionVacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_Cesante")
    private String  idCesante;

    @Column(name = "nombre_Cesante")
    private String nombreCesante;

    @Column(name = "celular_Cesante")
    private String celularCesante;

    @Column(name = "id_Vacante")
    private String vacanteId;

    @Column(name = "nombre_Vacante")
    private String nombreVacante;

    @Column(name = "salario_Vacante")
    private String salarioVacante;

    @Column(name = "horario_Vacante")
    private String horarioVacante;

    @Column(name = "ubicacion_Vacante")
    private String ubicacionVacante;

    @Column(name = "empresa_Vacante")
    private String empresaVacante;

    @OneToOne
    @JoinColumn(name = "id_Detalle")
    private DetalleSolicitud detalleSolicitud;

}
