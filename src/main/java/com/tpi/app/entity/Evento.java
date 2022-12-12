package com.tpi.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "eventos")
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_evento", length=20, nullable=false)
	private String nombre;
	
	@Column(name = "ubicacion_evento")
	private String ubicacion;
	
	@Column(name = "fecha_creacion_evento")
	private Date fechaCreacion;
	
	@JsonFormat(pattern="yyy-MM-dd HH:mm:ss")
	@Column(name = "fecha_realizacion_evento")
	private Date fechaRealizacion;
	
	@Column(name = "estado_evento") // campo que indique si está activo el evento (estará inactivo si la fecha del mismo ya pasó)
	private Boolean estado;
	
	@Column(name = "visibilidad_evento")
	private Boolean visibile;
	
	@ManyToOne
	@JoinColumn(name = "id_organizaciones")
	private Organizacion organizacion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Turno> turnos;

	public Evento() {
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public Boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}
	
}
