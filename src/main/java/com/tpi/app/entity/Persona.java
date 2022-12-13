package com.tpi.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "personas")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "El nombre no puede ser nulo")
	@Column(name = "nombre_persona", length=20, nullable=false)
	private String nombre;
	
	@NotNull(message = "El apellido no puede ser nulo")
	@Column(name = "apellido_persona", length=20, nullable=false)
	private String apellido;
	
	@NotNull(message = "El dni no puede ser nulo")
	@Size(min = 8, max = 8, message = "El DNI debe tener 8 digitos")
	@Column(name = "dni_persona", length=8, unique=true)
	private String dni;
	
	@Column(name = "estado_persona")
	private Boolean estado;
	
	@Column(name = "clave_persona", nullable=false)
	private String clave;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Turno> turnos;

	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
}
