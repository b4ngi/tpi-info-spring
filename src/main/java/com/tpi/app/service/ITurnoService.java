package com.tpi.app.service;

import java.util.List;

import com.tpi.app.dto.TurnoDto;

public interface ITurnoService {

	public TurnoDto guardar(TurnoDto turnoDto);
	
	public List<TurnoDto> findByOrganizacion(String organizacion);
	
	public List<TurnoDto> findByOrganizacionYEvento(String organizacion, String evento);

}
