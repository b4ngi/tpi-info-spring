package com.tpi.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpi.app.dao.IPersonaDao;

public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaDao personaDao;

}
