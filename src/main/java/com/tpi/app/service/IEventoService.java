package com.tpi.app.service;

import java.util.List;

import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Evento;

public interface IEventoService {

	public EventoDto guardar(EventoDto eventoDto);
	
	public EventoDto actualizar(EventoDto eventoDto);
	
	public void eliminar(EventoDto eventoDto);
	
	public List<Evento> findAll();
	
	public Evento findByNombre(String nombre);
}
