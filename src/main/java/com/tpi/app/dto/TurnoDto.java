package com.tpi.app.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TurnoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date fechaEvento;
	private String dniPersona;
	private String nombreOrganizacion;
	private String nombreEvento;
	
	public TurnoDto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	public String getDniPersona() {
		return dniPersona;
	}
	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}
	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}
	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	
}
