package com.tpi.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Evento;

@Repository
public interface IEventoDao extends JpaRepository<Evento, Long> {

	public Optional<Evento> findByNombre(String nombre);
	
}
