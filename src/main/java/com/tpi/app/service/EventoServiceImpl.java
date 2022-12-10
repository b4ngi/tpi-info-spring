package com.tpi.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpi.app.dao.IEventoDao;

public class EventoServiceImpl implements IEventoService {

	@Autowired
	private IEventoDao eventoDao;
}
