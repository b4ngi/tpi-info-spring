package com.tpi.app.service;

import java.util.List;

import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Evento;

public interface IEventoService {

	public EventoDto guardar(EventoDto eventoDto);
	
	public EventoDto actualizar(EventoDto eventoDto);
	
	public void eliminar(EventoDto eventoDto);
	
	public List<EventoDto> findAll();
	
	public Evento findByNombre(String nombre);

	public List<EventoDto> findByOrganizacion(String organizacion);
}
