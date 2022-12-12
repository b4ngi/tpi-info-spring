package com.tpi.app.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		if(nuevaOrganizacion == null) {
			response.put("mensaje", "Error al intentar guardar la nueva organizacion");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
		
		response.put("organizacion", nuevaOrganizacion);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}

	// Se envia en el cuerpo de la peticion la clave, y TODOS los campos(actualizados o no)
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String, Object>> actualizarOrganizacion(@RequestBody HashMap<String, Object> hashMap){
		Map<String, Object> response = new HashMap<>();
		
		Organizacion organizacion = organizacionService.findByNombre(""+hashMap.get("nombre"));
		
		if(organizacion == null) {
			response.put("mensaje", "No existe una organizacion con ese nombre.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
		
		String claveRecibida = ""+hashMap.get("clave");
		
		log.info(claveRecibida);
		log.info(organizacion.getClave());
		
		if(organizacion.getClave().equals(claveRecibida)){
			OrganizacionDto orgActualizada = organizacionService.actualizar(organizacion, hashMap);
			
			if(orgActualizada == null) {
				response.put("mensaje", "No se pudo actualizar la informacion de la organizacion.");
			}
			
			response.put("organizacion", orgActualizada);
		}else {
			response.put("mensaje", "Clave incorrecta");
		}
	return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Se envia en el cuerpo de la peticion la clave y el nombre de la organizacion
	@PutMapping("/eliminar")
	public ResponseEntity<Map<String, Object>> eliminarOrganizacion(@RequestBody HashMap<String, Object> hashMap){
		Map<String, Object> response = new HashMap<>();
		
		Organizacion organizacion = organizacionService.findByNombre(""+hashMap.get("nombre"));
		
		if(organizacion == null) {
			response.put("mensaje", "No existe una organizacion con ese nombre.");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
		
		String claveRecibida = ""+hashMap.get("clave");
		
		if(organizacion.getClave().equals(claveRecibida)) {
			long numEliminados = organizacionService.deleteByNombre(organizacion.getNombre());
			
			if(numEliminados == 1) {
				response.put("mensaje", "organizacion eliminada con exito");
			} else {
				response.put("mensaje", "error al eliminar la organizacion");
			}
		} else {
			response.put("mensaje", "Clave incorrecta");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> all(){
		Map<String, Object> response = new HashMap<>();
		
		List<Organizacion> organizaciones = organizacionService.findAll();
		List<OrganizacionDto> organizacionesMostrar = new ArrayList<OrganizacionDto>();
		
		for (int i=0;i<organizaciones.size();i++) {
		      
		      OrganizacionDto organizacionDto = OrganizacionWrapper.entityToDto(organizaciones.get(i));
		      organizacionesMostrar.add(organizacionDto);
		    }
		
		response.put("organizaciones", organizacionesMostrar);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/nombre/{nombreOrg}")
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
	
	@GetMapping("/cuit/{cuitOrg}")
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
