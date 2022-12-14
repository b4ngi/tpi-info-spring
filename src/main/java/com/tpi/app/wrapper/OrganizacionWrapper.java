package com.tpi.app.wrapper;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.restcontroller.OrganizacionRestController;

public class OrganizacionWrapper {
	private static final Logger log = LoggerFactory.getLogger(OrganizacionRestController.class);
	
	public static Organizacion dtoToEntity(OrganizacionDto dto, String clave) {
		if(dto == null) return new Organizacion();

		Organizacion entity = new Organizacion();
		entity.setNombre(dto.getNombre());
		entity.setCuit(dto.getCuit());
		entity.setDireccion(dto.getDireccion());
		entity.setTelefono(dto.getTelefono());
		entity.setEmail(dto.getEmail());
		entity.setClave(clave);
		entity.setFechaAlta(new Date());
		entity.setEstado(true);
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
		dto.setClave(entity.getClave());
		return dto;
	}
}
