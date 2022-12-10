package com.tpi.app.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.app.dto.OrganizacionDto;
import com.tpi.app.service.IOrganizacionService;

@RequestMapping("tpi/organizaciones")
@RestController
public class OrganizacionRestController {
	
	@Autowired
	private IOrganizacionService organizacionService;
	
	@PostMapping("/guardar")
	public ResponseEntity<Map<String, Object>> nuevaOrganizacion(@RequestBody OrganizacionDto organizacionDto){
		Map<String, Object> response = new HashMap<>();
		// OrganizacionDto nuevaOrganizacion = organizacionService.guardar(organizacionDto);
		organizacionService.guardar(organizacionDto);
		response.put("organizacion", organizacionDto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}
