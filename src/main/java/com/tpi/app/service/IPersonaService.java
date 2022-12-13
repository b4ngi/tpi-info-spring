package com.tpi.app.service;

import java.util.List;

import com.tpi.app.dto.PersonaDto;
import com.tpi.app.entity.Persona;

public interface IPersonaService {

	public String generarClave();
	
	public PersonaDto guardar(PersonaDto personaDto);
	
	public PersonaDto actualizar(PersonaDto personaDto);
	
	public void eliminar(PersonaDto personaDto);
	
	public List<PersonaDto> findAll();
	
	public List<PersonaDto> findByApellido(String apellido);
	
	public PersonaDto findByDni(String dni);
}
