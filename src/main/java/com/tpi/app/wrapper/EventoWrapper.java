package com.tpi.app.wrapper;

import java.util.Date;

import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;

public class EventoWrapper {
	public static Evento dtoToEntity(EventoDto dto, Organizacion organizacion) {
		if(dto == null) return new Evento();
		
		Evento entity = new Evento();
		entity.setNombre(dto.getNombre());
		entity.setUbicacion(dto.getUbicacion());
		entity.setFechaRealizacion(dto.getFechaRealizacion());
		entity.setOrganizacion(organizacion);
		entity.setFechaCreacion(new Date());
		entity.setEstado(true);
		entity.setVisibile(true);
		return entity;
	}
	
	public static EventoDto entityToDto(Evento entity) {
		if(entity == null) return new EventoDto();
		
		EventoDto dto = new EventoDto();
		dto.setNombre(entity.getNombre());
		dto.setUbicacion(entity.getUbicacion());
		dto.setFechaRealizacion(entity.getFechaRealizacion());
		dto.setOrganizacion(entity.getOrganizacion().getNombre());
		
		return dto;
	}
}
