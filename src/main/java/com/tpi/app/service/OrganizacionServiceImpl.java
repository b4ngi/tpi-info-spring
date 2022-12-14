package com.tpi.app.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.tpi.app.exceptions.KeyIsNull;
import com.tpi.app.exceptions.KeyNotEqual;
import com.tpi.app.exceptions.OrganizacionNoEncontrada;
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
		nuevaOrganizacion = organizacionDao.save(nuevaOrganizacion);
		organizacionDto = OrganizacionWrapper.entityToDto(nuevaOrganizacion);
		return organizacionDto;
	}
	
	public OrganizacionDto actualizar(OrganizacionDto organizacionDto) {
		
		Organizacion organizacion = organizacionDao.findByNombre(organizacionDto.getNombre()).orElseThrow(() -> new OrganizacionNoEncontrada());
		
		try {
			if(organizacionDto.getClave().equals(organizacion.getClave())) {
				organizacion.setNombre(organizacionDto.getNombre());
				organizacion.setCuit(organizacionDto.getCuit());
				organizacion.setDireccion(organizacionDto.getDireccion());
				organizacion.setEmail(organizacionDto.getEmail());
				organizacion.setTelefono(organizacionDto.getTelefono());
			} else throw new KeyNotEqual();
		} catch (Exception e) {throw new KeyIsNull();}

		return OrganizacionWrapper.entityToDto(organizacion);
	}
	
	@Override
	public List<OrganizacionDto> findAll(){
		List<Organizacion> organizaciones = organizacionDao.findAll();
		List<OrganizacionDto> organizacionesDto = new ArrayList<OrganizacionDto>();
		for (int i=0;i<organizaciones.size();i++) {
			//if(!organizaciones.get(i).getEstado()) continue;
			OrganizacionDto organizacionDto = OrganizacionWrapper.entityToDto(organizaciones.get(i));
			organizacionesDto.add(organizacionDto);
		}
		return organizacionesDto;
	}
	
	@Override
	public OrganizacionDto findById(Long id) {
		Organizacion organizacion = organizacionDao.findById(id).orElseThrow(() -> new OrganizacionNoEncontrada());
		return OrganizacionWrapper.entityToDto(organizacion);
	}
	
	@Override
	public OrganizacionDto findByNombre(String nombre) {
		Organizacion organizacion = organizacionDao.findByNombre(nombre).orElseThrow(() -> new OrganizacionNoEncontrada());
		return OrganizacionWrapper.entityToDto(organizacion);
	}
	
	@Override
	public OrganizacionDto findByCuit(Integer cuit) {
		Organizacion organizacion = organizacionDao.findByCuit(cuit).orElseThrow(() -> new OrganizacionNoEncontrada());
		return OrganizacionWrapper.entityToDto(organizacion);
	}
	
	@Override
	public long deleteByNombre(String nombre) {
		return organizacionDao.deleteByNombre(nombre);
	}
	
	@Override
	public void eliminar(OrganizacionDto organizacionDto) {
		Organizacion organizacion = organizacionDao.findByNombre(organizacionDto.getNombre()).orElseThrow(() -> new OrganizacionNoEncontrada());
		if(organizacionDto.getClave().equals(organizacion.getClave())) {
			organizacion.setEstado(false);
		} else throw new KeyNotEqual();
	}
}
