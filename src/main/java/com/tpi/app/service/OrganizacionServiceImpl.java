package com.tpi.app.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpi.app.dao.IOrganizacionDao;
import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;

@Service
public class OrganizacionServiceImpl implements IOrganizacionService {

	@Autowired
	private IOrganizacionDao organizacionDao;

	@Override
	public OrganizacionDto guardar(OrganizacionDto organizacionDto) {
		
		Organizacion nuevaOrganizacion = new Organizacion();
		
		nuevaOrganizacion.setNombre(organizacionDto.getNombre());
		nuevaOrganizacion.setCuit(organizacionDto.getCuit());
		nuevaOrganizacion.setDireccion(organizacionDto.getDireccion());
		nuevaOrganizacion.setTelefono(organizacionDto.getTelefono());
		nuevaOrganizacion.setEmail(organizacionDto.getEmail());
		nuevaOrganizacion.setClave("123aB");
		
		nuevaOrganizacion.setFechaAlta(new Date());
		
		//List<Evento> eventos = new List<Evento>();
		//nuevaOrganizacion.setEventos(eventos);
		
		organizacionDao.save(nuevaOrganizacion);
		
		return null;
	}
	
}
