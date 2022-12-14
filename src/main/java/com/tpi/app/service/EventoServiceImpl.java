package com.tpi.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpi.app.dao.IEventoDao;
import com.tpi.app.dao.IOrganizacionDao;
import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.exceptions.EventoNoEncontrado;
import com.tpi.app.exceptions.KeyIsNull;
import com.tpi.app.exceptions.KeyNotEqual;
import com.tpi.app.exceptions.NombreEventoExistente;
import com.tpi.app.exceptions.OrganizacionNoEncontrada;
import com.tpi.app.wrapper.EventoWrapper;

@Service
@Transactional
public class EventoServiceImpl implements IEventoService {

	@Autowired
	private IEventoDao eventoDao;
	
	@Autowired
	private IOrganizacionDao organizacionDao;

	// Guardar un evento -> Se verifica que exista la organizacion, que la clave sea correcta, y que no exista un evento con el mismo nombre de la misma organizacion
	@Override
	public EventoDto guardar(EventoDto eventoDto) {
		
		if(eventoDto.getClaveOrg() == null) throw new KeyIsNull();
		
		Organizacion organizacion = organizacionDao.findByNombre(eventoDto.getOrganizacion()).orElseThrow(() -> new OrganizacionNoEncontrada());
		
		if(!organizacion.getClave().equals(eventoDto.getClaveOrg())) {
			throw new KeyNotEqual();
		}
		
		List<Evento> eventosOrg = organizacion.getEventos();
		
		for(Evento evento: eventosOrg) {
			if(evento.getEstado() && eventoDto.getNombre().equals(evento.getNombre())) {
				throw new NombreEventoExistente();
			}
		}
		
		Evento nuevoEvento = EventoWrapper.dtoToEntity(eventoDto, organizacion);
		nuevoEvento = eventoDao.save(nuevoEvento);
		eventoDto = EventoWrapper.entityToDto(nuevoEvento);
		
		return eventoDto;
	}
	
	// Actualizar evento -> Se verifica que exista la organizacion, que la clave sea correcta y que el evento este activo
	@Override
	public EventoDto actualizar(EventoDto eventoDto) {
		if(eventoDto.getClaveOrg() == null) throw new KeyIsNull();
		
		Organizacion organizacion = organizacionDao.findByNombre(eventoDto.getOrganizacion()).orElseThrow(() -> new OrganizacionNoEncontrada());
		
		if(!organizacion.getClave().equals(eventoDto.getClaveOrg())) {
			throw new KeyNotEqual();
		}
		
		List<Evento> eventosOrganizacion = organizacion.getEventos();
		for(Evento evento: eventosOrganizacion) {
			if(evento.getEstado() && evento.getNombre().equals(eventoDto.getNombre())) {
				evento.setUbicacion(eventoDto.getUbicacion());
				evento.setFechaRealizacion(eventoDto.getFechaRealizacion());
				if(evento.getFechaRealizacion() == null) {
					evento.setTipo("recurrente");
				} else {
					evento.setTipo("unico");
				}
				evento = eventoDao.save(evento);
				return EventoWrapper.entityToDto(evento);
			}
		}
		throw new EventoNoEncontrado();
		
	}
	
	// Eliminar evento --> Se verifica que la organizacion exista, y que la clave sea correcta
	@Override
	public void eliminar(EventoDto eventoDto) {
		if(eventoDto.getClaveOrg() == null) throw new KeyIsNull();
		
		Organizacion organizacion = organizacionDao.findByNombre(eventoDto.getOrganizacion()).orElseThrow(() -> new OrganizacionNoEncontrada());
		
		if(!organizacion.getClave().equals(eventoDto.getClaveOrg())) {
			throw new KeyNotEqual();
		}
		
		List<Evento> eventosOrganizacion = organizacion.getEventos();
		for(Evento evento: eventosOrganizacion) {
			if(evento.getEstado() && evento.getNombre().equals(eventoDto.getNombre())) {
				evento.setEstado(false);
				evento = eventoDao.save(evento);
				return;
			}
		}
		throw new EventoNoEncontrado();
	}
	
	// Buscar todos los eventos ACTIVOS
	@Override
	public List<EventoDto> findAll(){
		List<Evento> eventos = eventoDao.findAll();
		List<EventoDto> eventosMostrar = new ArrayList<EventoDto>();
		
		for (int i=0;i<eventos.size();i++) {
			if(!eventos.get(i).getEstado()) continue;
			EventoDto eventoDto = EventoWrapper.entityToDto(eventos.get(i));
			eventosMostrar.add(eventoDto);
		}
		return eventosMostrar;
	}
	
	@Override
	public Evento findByNombre(String nombre) {
		return eventoDao.findByNombre(nombre).orElseThrow(() -> new EventoNoEncontrado());
	}

	// Buscar los eventos de una organizacion determinada
	@Override
	public List<EventoDto> findByOrganizacion(String organizacion) {
		Organizacion organizacionObj = organizacionDao.findByNombre(organizacion).orElseThrow(() -> new OrganizacionNoEncontrada());
		
		List<Evento> eventosOrganizacion = organizacionObj.getEventos();
		List<EventoDto> eventosDto = new ArrayList<>();
		
		for(Evento evento: eventosOrganizacion) {
			if(!evento.getEstado()) continue;
			EventoDto eventoDto = EventoWrapper.entityToDto(evento);
			eventosDto.add(eventoDto);
		}
		return eventosDto;
	}
}
	


