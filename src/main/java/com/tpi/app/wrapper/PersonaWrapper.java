package com.tpi.app.wrapper;

import com.tpi.app.dto.PersonaDto;
import com.tpi.app.entity.Persona;

public class PersonaWrapper {

	public static Persona dtoToEntity(PersonaDto dto, String clave) {
		if(dto == null) return new Persona();
		
		Persona entity = new Persona();
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDni(dto.getDni());
		entity.setEstado(true);
		entity.setClave(clave);
		return entity;
	}
	
	public static PersonaDto entityToDto(Persona entity) {
		if(entity == null) return new PersonaDto();
		
		PersonaDto dto = new PersonaDto();
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setDni(entity.getDni());
		dto.setClave(entity.getClave());
		return dto;
	}
	
}
