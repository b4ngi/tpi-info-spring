package com.tpi.app.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tpi.app.dao.IOrganizacionDao;
import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.wrapper.OrganizacionWrapper;

@Service
@Transactional
public class OrganizacionServiceImpl implements IOrganizacionService {
	private static final Logger log = LoggerFactory.getLogger(OrganizacionServiceImpl.class);

	@Autowired
	private IOrganizacionDao organizacionDao;

	@Override
	public String generarClave() {
	      UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");     

	      String random = ""+uid.randomUUID();
	      String cadena = "";
	      
	      for(int x = 0; x<8; x++){
	          cadena += random.charAt(x);
	      }
	    return cadena;
	}
	
	@Override
	public OrganizacionDto guardar(OrganizacionDto organizacionDto) {
		
		Organizacion nuevaOrganizacion = OrganizacionWrapper.dtoToEntity(organizacionDto, this.generarClave());
		//log.info(" "+nuevaOrganizacion.getId());
		
		if(nuevaOrganizacion == null) {
			return null;
		}
		
		nuevaOrganizacion = organizacionDao.save(nuevaOrganizacion);
		organizacionDto = OrganizacionWrapper.entityToDto(nuevaOrganizacion);
		
		return organizacionDto;
	}
	
	public OrganizacionDto actualizar(Organizacion organizacion, HashMap<String, Object> hashMap) {
		Organizacion organizacionActualizada = OrganizacionWrapper.hashMapToEntity(organizacion, hashMap);
		
		if(organizacionActualizada == null) {
			return null;
		}
		
		OrganizacionDto organizacionDto = OrganizacionWrapper.entityToDto(organizacionActualizada);

		return organizacionDto;
	}
	
	@Override
	public List<Organizacion> findAll(){
		return organizacionDao.findAll();
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
