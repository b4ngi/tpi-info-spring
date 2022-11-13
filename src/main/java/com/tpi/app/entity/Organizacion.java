package com.tpi.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name = "organizaciones")
public class Organizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_organizacion", length=20, nullable=false, unique=true)
	private String nombre;
	
	@Column(name = "cuit_organizacion")
	private Integer cuit;
	
	@Column(name = "direccion_organizacion")
	private String direccion;
	
	@Column(name = "telefono_organizacion")
	private Integer telefono;
	
	@Column(name = "email_organizacion")
	private String email;
	
	@Column(name = "fecha_alta_organizacion")
	private Date fechaAlta;
	
	@Column(name = "clave_organizacion")
	private String clave;
	
	public Organizacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getCuit() {
		return cuit;
	}
	
	public void setCuit(Integer cuit) {
		this.cuit = cuit;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Integer getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public String getClave() {
		return clave;
	}
	
}
