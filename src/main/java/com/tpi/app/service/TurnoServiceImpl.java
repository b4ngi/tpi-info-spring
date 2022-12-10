package com.tpi.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpi.app.dao.ITurnoDao;

public class TurnoServiceImpl implements ITurnoService {

	@Autowired
	private ITurnoDao turnoDao;
}
