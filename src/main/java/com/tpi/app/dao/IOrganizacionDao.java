package com.tpi.app.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Organizacion;

@Repository
public interface IOrganizacionDao extends JpaRepository<Organizacion, Long>{

	public Optional<Organizacion> findById(Long id);
	
	public Optional<Organizacion> findByNombre(String nombre);
	
	public Optional<Organizacion> findByCuit(Integer cuit);
	
	public long deleteByNombre(String nombre);
	
}
