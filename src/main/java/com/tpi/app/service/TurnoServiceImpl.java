package com.tpi.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.app.dao.IEventoDao;
import com.tpi.app.dao.IOrganizacionDao;
import com.tpi.app.dao.IPersonaDao;
import com.tpi.app.dao.ITurnoDao;
import com.tpi.app.dto.TurnoDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.entity.Persona;
import com.tpi.app.entity.Turno;
import com.tpi.app.exceptions.EventoNoEncontrado;
import com.tpi.app.exceptions.KeyNotEqual;
import com.tpi.app.exceptions.OrganizacionNoEncontrada;
import com.tpi.app.exceptions.PersonaNoEncontrada;
import com.tpi.app.wrapper.TurnoWrapper;

@Service
@Transactional
public class TurnoServiceImpl implements ITurnoService {

	@Autowired
	private ITurnoDao turnoDao;
	
	@Autowired
	private IOrganizacionDao organizacionDao;
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private IEventoDao eventoDao;

	@Override
	public TurnoDto guardar(TurnoDto turnoDto) {
		Persona persona = personaDao.findByDni(turnoDto.getDniPersona()).orElseThrow(() -> new PersonaNoEncontrada());
		Organizacion organizacion = organizacionDao.findByNombre(turnoDto.getNombreOrganizacion()).orElseThrow(() -> new OrganizacionNoEncontrada());
		
		for(Evento evento: organizacion.getEventos()) {
			if(evento.getEstado() && evento.getNombre().equals(turnoDto.getNombreEvento())) {
				Turno nuevoTurno = TurnoWrapper.dtoToEntity(turnoDto, persona, evento);
				nuevoTurno = turnoDao.save(nuevoTurno);
				turnoDto = TurnoWrapper.entityToDto(nuevoTurno);
				return turnoDto;
			}
		}
		throw new EventoNoEncontrado();
	}
	
	public List<TurnoDto> findByOrganizacion(String organizacion){
		Organizacion organizacionObj = organizacionDao.findByNombre(organizacion).orElseThrow(() -> new OrganizacionNoEncontrada());
		List<TurnoDto> turnosDto = new ArrayList<TurnoDto>();
		
		for(Evento evento: organizacionObj.getEventos()) {
			if(!evento.getEstado()) continue;	// si el evento esta inactivo lo paso
			for(Turno turno: evento.getTurnos()) {
				TurnoDto turnoDto = TurnoWrapper.entityToDto(turno);
				turnosDto.add(turnoDto);
			}
		}
		return turnosDto;
	}

	@Override
	public List<TurnoDto> findByOrganizacionYEvento(String organizacion, String evento) {
		Organizacion organizacionObj = organizacionDao.findByNombre(organizacion).orElseThrow(() -> new OrganizacionNoEncontrada());
		List<String> nombresEventos = new ArrayList<String>();
		List<TurnoDto> turnosDto = new ArrayList<TurnoDto>();
		
		for(Evento eventoObj: organizacionObj.getEventos()) {
			nombresEventos.add(eventoObj.getNombre());
		}
		if(!nombresEventos.contains(evento)) throw new EventoNoEncontrado();
		
		// Esta parte es mejorable!!!!
		for(Evento eventoObj: organizacionObj.getEventos()) {
			if(eventoObj.getEstado() && eventoObj.getNombre().equals(evento)) {
				for(Turno turno: eventoObj.getTurnos()) {
					if(!turno.getEstado()) continue; // si el turno esta inactivo lo paso
					TurnoDto turnoDto = TurnoWrapper.entityToDto(turno);
					turnosDto.add(turnoDto);
				}
			}
		}
		return turnosDto;
	}
	
}
