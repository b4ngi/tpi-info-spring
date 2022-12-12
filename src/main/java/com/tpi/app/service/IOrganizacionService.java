package com.tpi.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;

public interface IOrganizacionService {

	public String generarClave();
	
	public OrganizacionDto guardar(OrganizacionDto organizacionDto);
	
	public OrganizacionDto actualizar(Organizacion organizacion, HashMap<String, Object> hashMap);
	
	public Optional<Organizacion> findById(Long id);
	
	public Organizacion findByNombre(String nombre);
	
	public Organizacion findByCuit(Integer cuit);
	
	public List<Organizacion> findAll();
	
	public long deleteByNombre(String nombre);	// devuelve la cantidad de registros eliminados. Como el nombre es campo unico, si elimina con exito devuelve 1.
}
