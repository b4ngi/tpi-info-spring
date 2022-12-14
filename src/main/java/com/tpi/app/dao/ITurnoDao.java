package com.tpi.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpi.app.entity.Turno;

@Repository
public interface ITurnoDao extends JpaRepository<Turno, Long> {

}
