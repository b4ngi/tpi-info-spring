package com.tpi.app.service;

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
import com.tpi.app.exceptions.OrganizacionNoEncontrada;
import com.tpi.app.wrapper.EventoWrapper;

@Service
@Transactional
public class EventoServiceImpl implements IEventoService {

	@Autowired
	private IEventoDao eventoDao;
	
	@Autowired
	private IOrganizacionDao organizacionDao;
	
	@Autowired
	private IOrganizacionService organizacionService;

	@Override
	public EventoDto guardar(EventoDto eventoDto) {
		
		Organizacion organizacion = organizacionDao.findByNombre(eventoDto.getOrganizacion()).orElseThrow(() -> new OrganizacionNoEncontrada());
		List<Evento> eventosOrg = organizacion.getEventos();
		
		for(Evento evento: eventosOrg) {
			if(evento.getVisibile() && eventoDto.getNombre().equals(evento.getNombre())) {
				return null;
			}
		}
		
		if(!organizacion.getClave().equals(eventoDto.getClaveOrg())) {
			return null;
		}
		
		Evento nuevoEvento = EventoWrapper.dtoToEntity(eventoDto, organizacion);
		nuevoEvento = eventoDao.save(nuevoEvento);
		eventoDto = EventoWrapper.entityToDto(nuevoEvento);
		
		return eventoDto;
	}
	
	@Override
	public EventoDto actualizar(EventoDto eventoDto) {
		Organizacion organizacion = organizacionDao.findByNombre(eventoDto.getOrganizacion()).orElseThrow(() -> new OrganizacionNoEncontrada());
		if(!organizacion.getClave().equals(eventoDto.getClaveOrg())) {
			return null;
		}
		return null;
		
	}
	
	@Override
	public String eliminar(HashMap<String, Object> hashMap) {
		Evento evento = this.findByNombre(""+hashMap.get("nombreEvento"));
		
		Organizacion organizacion = organizacionDao.findByNombre(""+hashMap.get("nombreOrganizacion")).orElseThrow(() -> new OrganizacionNoEncontrada());
		if(organizacion == null) {
			return "nombre de organizacion incorrecta";
		}
		List<Evento> eventosOrg = organizacion.getEventos();
		
		if(!organizacion.getClave().equals(hashMap.get("claveOrg"))) {
			return null;
		}
		
		if(eventosOrg.contains(evento)) {
			evento.setVisibile(false);
			return "evento eliminado con exito";
		}
		return "error al intentar eliminar el evento";
	}
	
	@Override
	public List<Evento> findAll(){
		return eventoDao.findAll();
	}
	
	@Override
	public Evento findByNombre(String nombre) {
		return eventoDao.findByNombre(nombre);
	}
}
	


