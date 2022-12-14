package com.middleware.colsubsidio.AgenciaEmpleo.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "detalle_response_chatbot")
@Entity
public class DetalleResponseChatbot{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long Id;

  @Column(name = "id_detalle_solicitud", nullable = true)
  private Long idDetalleSolicitud;

  @Column(name = "response_chatbot")
  private String responseChatBot;

  @Column(name = "celular")
  private String celular;
  
  @Column(name = "interes")
  private int interes;
  
  @Column(name = "contactar")
  private int contactar;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_registro",nullable = true)
  private Date fechaRegistro;

  @Column(name = "estado_envio_agencia", nullable = true)
  private int estado;

  @Column(name = "respuesta_peticion_agencia", nullable = true)
  private String respuestaPeticionAgencia;

  @Column(name = "fecha_respuesta", nullable = true)
  private Date fechaRespuestaAgencia;


}
