package com.tpi.app.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;

@ControllerAdvice
public class ControllerAdvisorGlobalException extends ResponseEntityExceptionHandler {
    
	@ExceptionHandler(KeyNotEqual.class)
    public ResponseEntity<Object> handleOrganizationKeyNotEqual() {
        
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Clave incorrecta.");
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(PersonaNoEncontrada.class)
	public ResponseEntity<Object> handlePersonaNoEncontrada() {
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Persona no encontrada.");
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationException(javax.validation.ConstraintViolationException e){
        Map<String,Object> response = new HashMap<>();
        List<String> errorString = e.getConstraintViolations().stream().map(error -> error.getMessage().toString()).collect(Collectors.toList());
        response.put("message", errorString.toString());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException e){
		Map<String,Object> response = new HashMap<>();
		response.put("message", e.getMostSpecificCause().getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
	}
	
}
