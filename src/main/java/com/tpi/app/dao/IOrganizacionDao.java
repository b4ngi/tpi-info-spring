package com.tpi.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Organizacion;

@Repository
public interface IOrganizacionDao extends JpaRepository<Organizacion, Long>{

	public Organizacion findByNombre(String nombre);
	
	public Organizacion findByCuit(Integer cuit);
	
	public long deleteByNombre(String nombre);
	
}
