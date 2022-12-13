package com.tpi.app.dto;

import java.io.Serializable;

public class PersonaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String apellido;
	
	private String dni;
	
	private String clave;
	
	public PersonaDto() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
