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

import com.tpi.app.dto.PersonaDto;
import com.tpi.app.service.IPersonaService;

@RequestMapping("tpi/persona")
@RestController
public class PersonaRestController {
	
	@Autowired
	private IPersonaService personaService;
	
	// En el cuerpo de la peticion se deben enviar los campos: nombre, apellido, dni. La clave se autogenera.
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> nuevaPersona(@Valid @RequestBody PersonaDto personaDto) {
		Map<String, Object> response = new HashMap<>();
		response.put("persona", personaService.guardar(personaDto));
		response.put("mensaje", "Persona registrada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		response.put("personas", personaService.findAll());
		response.put("mensaje", "Busqueda realizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/apellido/{apellido}")
	public ResponseEntity<Map<String, Object>> buscarPorApellido(@PathVariable(name = "apellido") String apellido){
		Map<String, Object> response = new HashMap<>();
		response.put("personas", personaService.findByApellido(apellido));
		response.put("mensaje", "Busqueda realizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/dni/{dni}")
	public ResponseEntity<Map<String, Object>> buscarPorDni(@PathVariable(name = "dni") String dni){
		Map<String, Object> response = new HashMap<>();
		response.put("personas", personaService.findByDni(dni));
		response.put("mensaje", "Busqueda realizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	// En el cuerpo de la peticion se debe enviar TODOS los campos, inclusive si no se desea actualizar un campo(enviarlo con el valor anterior) y la CLAVE.
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizar(@RequestBody PersonaDto personaDto){
		Map<String, Object> response = new HashMap<>();
		response.put("persona", personaService.actualizar(personaDto));
		response.put("mensaje", "Persona actualizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	// En el cuerpo de la peticion se debe enviar el DNI y la CLAVE de la persona
	@PutMapping("/eliminar")
	public ResponseEntity<Map<String, Object>> eliminar(@RequestBody PersonaDto personaDto){
		Map<String, Object> response = new HashMap<>();
		personaService.eliminar(personaDto);
		response.put("mensaje", "Persona eliminada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
}
