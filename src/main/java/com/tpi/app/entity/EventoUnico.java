package com.tpi.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "eventos_unicos")
public class EventoUnico extends Evento {
	
	@Column(name = "fecha_realizacion_evento")
	private Date fechaRealizacion;
	
	@Column(name = "estado_evento") // campo que indique si está activo el evento (estará inactivo si la fecha del mismo ya pasó)
	private Boolean estado;
}