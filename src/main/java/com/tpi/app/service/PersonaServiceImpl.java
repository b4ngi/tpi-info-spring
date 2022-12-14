package com.tpi.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpi.app.dao.IPersonaDao;
import com.tpi.app.dto.PersonaDto;
import com.tpi.app.entity.Persona;
import com.tpi.app.exceptions.KeyIsNull;
import com.tpi.app.exceptions.KeyNotEqual;
import com.tpi.app.exceptions.PersonaNoEncontrada;
import com.tpi.app.wrapper.PersonaWrapper;

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaDao personaDao;

	// Generar clave de persona -> 8 digitos alfanumericos aleatorios
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
	
	// Guardar la persona en la base de datos
	@Override
	public PersonaDto guardar(PersonaDto personaDto) {
		Persona nuevaPersona = PersonaWrapper.dtoToEntity(personaDto, this.generarClave());
		nuevaPersona = personaDao.save(nuevaPersona);
		personaDto = PersonaWrapper.entityToDto(nuevaPersona);
		return personaDto;
	}

	// Actualizar los campos de la persona
	@Override
	public PersonaDto actualizar(PersonaDto personaDto) {
		if(personaDto.getClave() == null) throw new KeyIsNull();
		
		Persona persona = personaDao.findByDni(personaDto.getDni()).orElseThrow(() -> new PersonaNoEncontrada());
		
		if(personaDto.getClave().equals(persona.getClave())) {
			persona.setNombre(personaDto.getNombre());
			persona.setApellido(personaDto.getApellido());
			persona.setDni(personaDto.getDni());
			persona = personaDao.save(persona);
		} else throw new KeyNotEqual();
		
		return PersonaWrapper.entityToDto(persona);
	}
	
	// Eliminacion logica de una persona -> Atributo estado FALSE
	@Override
	public void eliminar(PersonaDto personaDto) {
		if(personaDto.getClave() == null) throw new KeyIsNull();
		
		Persona persona = personaDao.findByDni(personaDto.getDni()).orElseThrow(() -> new PersonaNoEncontrada());
		
		if(personaDto.getClave().equals(persona.getClave())) {
			persona.setEstado(false);
			persona = personaDao.save(persona);
		} else {throw new KeyNotEqual();}
	}
	
	// Buscar todos las personas ACTIVAS
	@Override
	public List<PersonaDto> findAll() {
		List<Persona> personas = personaDao.findAll();
		List<PersonaDto> personasDto = new ArrayList<PersonaDto>();
		for (int i=0;i<personas.size();i++) {
			if(!personas.get(i).getEstado()) continue;
			PersonaDto dto = PersonaWrapper.entityToDto(personas.get(i));
			personasDto.add(dto);
		}
		return personasDto;
	}

	// Buscar por apellido solo las personas ACTIVAS
	@Override
	public List<PersonaDto> findByApellido(String apellido) {
		List<Persona> personas = personaDao.findByApellido(apellido);
		List<PersonaDto> personasDto = new ArrayList<PersonaDto>();
		for (int i=0;i<personas.size();i++) {
			if(!personas.get(i).getEstado()) continue;
			PersonaDto dto = PersonaWrapper.entityToDto(personas.get(i));
			personasDto.add(dto);
		}
		return personasDto;
	}

	// Buscar una persona por DNI. No importa si esta activa o no
	@Override
	public PersonaDto findByDni(String dni) {
		Persona persona = personaDao.findByDni(dni).orElseThrow(() -> new PersonaNoEncontrada());
		return PersonaWrapper.entityToDto(persona);
	}
	
}
