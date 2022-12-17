package com.tpi.app.wrapper;

import java.util.Date;

import com.tpi.app.dto.TurnoDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Persona;
import com.tpi.app.entity.Turno;
import com.tpi.app.exceptions.FechaNecesaria;

public class TurnoWrapper {
	
	public static Turno dtoToEntity(TurnoDto dto, Persona persona, Evento evento) {
		if(dto == null) return new Turno();
		
		Turno entity = new Turno();
		entity.setCodigo("#00000");		// ## Deberia ser un codigo autogenerado ##
		entity.setEvento(evento);
		entity.setPersona(persona);
		entity.setFechaCreacion(new Date());
		entity.setEstado(true);
		
		if(evento.getFechaRealizacion() == null) {
				entity.setFechaEvento(dto.getFechaEvento());
		} else {
			entity.setFechaEvento(evento.getFechaRealizacion());
		}
		
		if (entity.getFechaEvento() == null) throw new FechaNecesaria();
		
		
		return entity;
	}
	
	public static TurnoDto entityToDto(Turno entity) {
		if(entity == null) return new TurnoDto();
		
		TurnoDto dto = new TurnoDto();
		dto.setCodigo(entity.getCodigo());
		dto.setDniPersona(entity.getPersona().getDni());
		dto.setNombreEvento(entity.getEvento().getNombre());
		dto.setNombreOrganizacion(entity.getEvento().getOrganizacion().getNombre());
		dto.setFechaEvento(entity.getFechaEvento());
		
		return dto;
	}
	
}
