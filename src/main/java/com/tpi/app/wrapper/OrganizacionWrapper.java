package com.tpi.app.wrapper;

import java.util.Date;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;

public class OrganizacionWrapper {

	public static Organizacion dtoToEntity(OrganizacionDto dto) {
		if(dto == null) return new Organizacion();
		
		Organizacion entity = new Organizacion();
		entity.setNombre(dto.getNombre());
		entity.setCuit(dto.getCuit());
		entity.setDireccion(dto.getDireccion());
		entity.setTelefono(dto.getTelefono());
		entity.setEmail(dto.getEmail());
		entity.setClave("123aB");
		entity.setFechaAlta(new Date());
		return entity;
	}
	
	public static OrganizacionDto entityToDto(Organizacion entity) {
		if(entity == null) return new OrganizacionDto();
		
		OrganizacionDto dto = new OrganizacionDto();
		dto.setNombre(entity.getNombre());
		dto.setCuit(entity.getCuit());
		dto.setDireccion(entity.getDireccion());
		dto.setTelefono(entity.getTelefono());
		dto.setEmail(entity.getEmail());
		
		return dto;
	}
	
}
