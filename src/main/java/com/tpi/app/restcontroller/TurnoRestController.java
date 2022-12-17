package com.tpi.app.restcontroller;

import java.util.HashMap;
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
import com.tpi.app.dto.TurnoDto;
import com.tpi.app.service.ITurnoService;

@RequestMapping("tpi/turno")
@RestController
public class TurnoRestController {

	@Autowired
	private ITurnoService turnoService;
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> nuevoTurno(@Valid @RequestBody TurnoDto turnoDto){
		Map<String, Object> response = new HashMap<>();
		response.put("turno", turnoService.guardar(turnoDto));
		response.put("mensaje", "Turno guardado con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/organizacion/{organizacion}")
	public ResponseEntity<Map<String, Object>> buscarPorOrganizacion(@PathVariable(name="organizacion") String organizacion){
		Map<String, Object> response = new HashMap<>();
		response.put("turnos", turnoService.findByOrganizacion(organizacion));
		response.put("mensaje", "Busqueda realizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/organizacion/{organizacion}/evento/{evento}")
	public ResponseEntity<Map<String, Object>> buscarPorOrganizacionYEvento(@PathVariable(name="organizacion") String organizacion, @PathVariable(name="evento") String evento){
		Map<String, Object> response = new HashMap<>();
		response.put("turnos", turnoService.findByOrganizacionYEvento(organizacion, evento));
		response.put("mensaje", "Busqueda realizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}
