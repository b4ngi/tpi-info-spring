package com.tpi.app.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.service.IOrganizacionService;
import com.tpi.app.wrapper.OrganizacionWrapper;

@RequestMapping("tpi/organizacion")
@RestController
public class OrganizacionRestController {
	private static final Logger log = LoggerFactory.getLogger(OrganizacionRestController.class);
	
	@Autowired
	private IOrganizacionService organizacionService;
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String, Object>> nuevaOrganizacion(@Valid @RequestBody OrganizacionDto organizacionDto){
		Map<String, Object> response = new HashMap<>();
		response.put("organizacion", organizacionService.guardar(organizacionDto));
		response.put("mensaje", "Organizacion registrada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

	// Se envia en el cuerpo de la peticion la clave, y TODOS los campos(actualizados o no)
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizarOrganizacion(@RequestBody OrganizacionDto organizacionDto){
		Map<String, Object> response = new HashMap<>();
		response.put("organizacion", organizacionService.actualizar(organizacionDto));
		response.put("mensaje", "Organizacion actualizada con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Se envia en el cuerpo de la peticion la clave y el nombre de la organizacion
	@PutMapping("/eliminar")
	public ResponseEntity<Map<String, Object>> eliminarOrganizacion(@RequestBody OrganizacionDto organizacionDto){
		Map<String, Object> response = new HashMap<>();
		organizacionService.eliminar(organizacionDto);
		response.put("mensaje", "Organizacion eliminada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		response.put("organizaciones", organizacionService.findAll());
		response.put("mensaje", "Busqueda realizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/nombre/{nombreOrg}")
	public ResponseEntity<Map<String, Object>> buscarPorNombre(@PathVariable(name = "nombreOrg") String nombreOrg){
		Map<String, Object> response = new HashMap<>();
		response.put("organizacion", organizacionService.findByNombre(nombreOrg));
		response.put("mensaje", "busqueda finalizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/cuit/{cuitOrg}")
	public ResponseEntity<Map<String, Object>> buscarPorCuit(@PathVariable(name = "cuitOrg") Integer cuitOrg){
		Map<String, Object> response = new HashMap<>();
		response.put("organizacion", organizacionService.findByCuit(cuitOrg));
		response.put("mensaje", "busqueda finalizada con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}
