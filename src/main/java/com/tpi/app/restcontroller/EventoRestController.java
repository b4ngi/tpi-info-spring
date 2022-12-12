package com.tpi.app.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.app.dto.EventoDto;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.service.IEventoService;

@RequestMapping("tpi/evento")
@RestController
public class EventoRestController {
	
	@Autowired
	private IEventoService eventoService;
	
	@PostMapping("/nuevo")
	public ResponseEntity<Map<String, Object>> nuevoEvento(@RequestBody EventoDto eventoDto){
		Map<String, Object> response = new HashMap<>();
		
		EventoDto nuevoEvento = eventoService.guardar(eventoDto);
		
		if(nuevoEvento == null) {
			response.put("mensaje", "clave de organizacion incorrecta o nombre de evento existente");
		} else {
			response.put("evento", nuevoEvento);
			response.put("mensaje", "Evento guardado con exito");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizarEvento(@RequestBody EventoDto eventoDto){
		Map<String, Object> response = new HashMap<>();
		
		//EventoDto eventoActualizado = eventoService.actualizar(eventoDto);
		return null;
	}
	
	@PutMapping("/eliminar")
	public ResponseEntity<Map<String, Object>> eliminarEvento(@RequestBody HashMap<String, Object> hashMap){
		Map<String, Object> response = new HashMap<>();
		
		String respuesta = eventoService.eliminar(hashMap);
		
		if(respuesta == null) {
			response.put("mensaje", "clave de organizacion incorrecta");
		} else {
			response.put("mensaje", respuesta);
		}
	return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}
