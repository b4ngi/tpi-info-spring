package com.tpi.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Persona;

@Repository
public interface IPersonaDao extends JpaRepository<Persona, Long> {

}
