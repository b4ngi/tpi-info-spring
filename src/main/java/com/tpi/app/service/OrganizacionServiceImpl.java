package com.tpi.app.service;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpi.app.dao.IOrganizacionDao;
import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.wrapper.OrganizacionWrapper;

@Service
@Transactional
public class OrganizacionServiceImpl implements IOrganizacionService {

	@Autowired
	private IOrganizacionDao organizacionDao;

	@Override
	public OrganizacionDto guardar(OrganizacionDto organizacionDto) {
		
		Organizacion nuevaOrganizacion = OrganizacionWrapper.dtoToEntity(organizacionDto);
		nuevaOrganizacion = organizacionDao.save(nuevaOrganizacion);
		organizacionDto = OrganizacionWrapper.entityToDto(nuevaOrganizacion);
		
		return organizacionDto;
	}
	
	@Override
	public Organizacion findByNombre(String nombre) {
		return organizacionDao.findByNombre(nombre);
	}
	
	@Override
	public Organizacion findByCuit(Integer cuit) {
		return organizacionDao.findByCuit(cuit);
	}
	
	@Override
	public long deleteByNombre(String nombre) {
		return organizacionDao.deleteByNombre(nombre);
	}
}
