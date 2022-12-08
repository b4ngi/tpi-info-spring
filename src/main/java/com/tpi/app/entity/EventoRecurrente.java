package com.tpi.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "eventos_recurrentes")
public class EventoRecurrente extends Evento {
	
	@Column(name = "fecha_turno")
	private Date fechaTurno;
	
}
