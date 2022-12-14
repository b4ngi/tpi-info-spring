package com.tpi.app.service;

import java.util.List;
import java.util.Optional;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;

public interface IOrganizacionService {

	public String generarClave();
	
	public OrganizacionDto guardar(OrganizacionDto organizacionDto);
	
	public OrganizacionDto actualizar(OrganizacionDto organizacionDto);
	
	public OrganizacionDto findById(Long id);
	
	public OrganizacionDto findByNombre(String nombre);
	
	public OrganizacionDto findByCuit(String cuit);
	
	public List<OrganizacionDto> findAll();
	
	public long deleteByNombre(String nombre);	// devuelve la cantidad de registros eliminados. Como el nombre es campo unico, si elimina con exito devuelve 1.

	void eliminar(OrganizacionDto organizacionDto);
}
