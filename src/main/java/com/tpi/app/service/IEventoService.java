package com.tpi.app.service;

import java.util.HashMap;

import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Evento;

public interface IEventoService {

	public EventoDto guardar(EventoDto eventoDto);
	
	public EventoDto actualizar(EventoDto eventoDto);
	
	public String eliminar(HashMap<String, Object> hashMap);
	
	public Evento findByNombre(String nombre);
}