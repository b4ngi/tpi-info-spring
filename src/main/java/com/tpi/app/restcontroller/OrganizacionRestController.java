package com.tpi.app.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.entity.Organizacion;
import com.tpi.app.service.IOrganizacionService;
import com.tpi.app.wrapper.OrganizacionWrapper;

@RequestMapping("tpi/organizaciones")
@RestController
public class OrganizacionRestController {
	private static final Logger log = LoggerFactory.getLogger(OrganizacionRestController.class);
	
	@Autowired
	private IOrganizacionService organizacionService;
	
	@PostMapping("/guardar")
	public ResponseEntity<Map<String, Object>> nuevaOrganizacion(@RequestBody OrganizacionDto organizacionDto){
		Map<String, Object> response = new HashMap<>();
		OrganizacionDto nuevaOrganizacion = organizacionService.guardar(organizacionDto);
		response.put("organizacion", nuevaOrganizacion);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizarOrganizacion(@RequestBody OrganizacionDto organizacionDto){
		Map<String, Object> response = new HashMap<>();
		
		OrganizacionDto orgActualizada = organizacionService.guardar(organizacionDto);
		
		if(orgActualizada == null) {
			response.put("mensaje", "No se pudo actualizar la informacion del paciente.");
		}
		
		response.put("organizacion", orgActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

	@GetMapping("/eliminar/{nombreOrg}")
	public ResponseEntity<Map<String, Object>> eliminarOrganizacion(@PathVariable(name = "nombreOrg") String nombreOrg){
		Map<String, Object> response = new HashMap<>();
		
		long numEliminados = organizacionService.deleteByNombre(nombreOrg);
		
		if(numEliminados == 1) {
			response.put("mensaje", "organizacion eliminada con exito");
		} else {
			response.put("mensaje", "error al eliminar la organizacion");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("nombre/{nombreOrg}")
	public ResponseEntity<Map<String, Object>> buscarPorNombre(@PathVariable(name = "nombreOrg") String nombreOrg){
		Map<String, Object> response = new HashMap<>();
		
		Organizacion organizacion = organizacionService.findByNombre(nombreOrg);
		if(organizacion == null) {
			response.put("mensaje", "no hay resultados.");
		} else {
			OrganizacionDto organizacionResp = OrganizacionWrapper.entityToDto(organizacion);
			response.put("organizacion", organizacionResp);
			response.put("mensaje", "busqueda finalizada con exito");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("cuit/{cuitOrg}")
	public ResponseEntity<Map<String, Object>> buscarPorCuit(@PathVariable(name = "cuitOrg") Integer cuitOrg){
		Map<String, Object> response = new HashMap<>();
		
		Organizacion organizacion = organizacionService.findByCuit(cuitOrg);
		if(organizacion == null) {
			response.put("mensaje", "no hay resultados.");
		} else {
			OrganizacionDto organizacionResp = OrganizacionWrapper.entityToDto(organizacion);
			response.put("organizacion", organizacionResp);
			response.put("mensaje", "busqueda finalizada con exito");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}
