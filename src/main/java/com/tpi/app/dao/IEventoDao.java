package com.tpi.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Evento;

@Repository
public interface IEventoDao extends JpaRepository<Evento, Long> {

}
