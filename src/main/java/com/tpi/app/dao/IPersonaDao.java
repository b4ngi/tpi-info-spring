package com.tpi.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Persona;

@Repository
public interface IPersonaDao extends JpaRepository<Persona, Long> {

	public List<Persona> findByApellido(String apellido);
	
	public Optional<Persona> findByDni(String dni);
	
}
