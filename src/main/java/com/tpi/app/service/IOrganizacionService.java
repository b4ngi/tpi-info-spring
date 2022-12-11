package com.tpi.app.service;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;

public interface IOrganizacionService {

	public OrganizacionDto guardar(OrganizacionDto organizacionDto);
	
	public Organizacion findByNombre(String nombre);
	
	public Organizacion findByCuit(Integer cuit);
	
	public long deleteByNombre(String nombre);	// devuelve la cantidad de registros eliminados. Como el nombre es campo unico, si elimina con exito devuelve 1.
}
