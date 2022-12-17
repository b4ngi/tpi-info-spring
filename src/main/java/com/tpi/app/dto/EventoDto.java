package com.tpi.app.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String ubicacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date fechaRealizacion;
	private String organizacion;
	private String claveOrg;
	private String tipo;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}
	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	public String getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	
	public String getClaveOrg() {
		return claveOrg;
	}
	public void setClaveOrg(String claveOrg) {
		this.claveOrg = claveOrg;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
