package com.tpi.app.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Evento;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.exceptions.EventoNoEncontrado;
import com.tpi.app.service.IEventoService;
import com.tpi.app.wrapper.EventoWrapper;

@RequestMapping("tpi/evento")
@RestController
public class EventoRestController {
	
	@Autowired
	private IEventoService eventoService;
	
	// Registrar un evento -> Si no se especifica una fecha de realizacion, se supone que es un evento recurrente
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> nuevoEvento(@Valid @RequestBody EventoDto eventoDto) {
		Map<String, Object> response = new HashMap<>();
		response.put("evento", eventoService.guardar(eventoDto));
		response.put("mensaje", "Evento guardado con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	// Se puede actualizar un evento, especificando el nombre del evento, asi como el nombre de la organizacion a la cual pertenece y su clave
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizarEvento(@Valid @RequestBody EventoDto eventoDto){
		Map<String, Object> response = new HashMap<>();
		response.put("evento", eventoService.actualizar(eventoDto));
		response.put("mensaje", "Evento actualizado con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	// Eliminacion de un evento(logica -> Atributo estado FALSE)
	@PutMapping("/eliminar")
	public ResponseEntity<Map<String, Object>> eliminarEvento(@RequestBody EventoDto eventoDto){
		Map<String, Object> response = new HashMap<>();
		eventoService.eliminar(eventoDto);
		response.put("mensaje", "Evento eliminado con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		response.put("organizaciones", eventoService.findAll());
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	// Buscar los eventos de una organizacion determinada
	@GetMapping("organizacion/{organizacion}")
	public ResponseEntity<Map<String, Object>> buscarPorOrganizacion(@PathVariable(name = "organizacion") String organizacion){
		Map<String, Object> response = new HashMap<>();
		response.put("organizaciones", eventoService.findByOrganizacion(organizacion));
		response.put("mensaje", "Busqueda finalizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}
