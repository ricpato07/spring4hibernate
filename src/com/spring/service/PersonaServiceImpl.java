package com.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dao.PersonaDao;
import com.spring.pojo.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaDao personaDao;

	public void save(Persona objeto) {
		personaDao.save(objeto);
	}

	public void update(Persona objeto) {
		personaDao.update(objeto);
	}

	public void delete(int id) {
		Persona admin = personaDao.findById(id);
		personaDao.delete(admin);
	}

	public List<Persona> findAll() {
		return personaDao.findAll();
	}

	public Persona findById(int id) {
		return personaDao.findById(id);
	}

	public List<Persona> findByNombre(String nombre) {
		if (nombre != null) {
			return personaDao.findByNombre(nombre);
		} else {
			return personaDao.findAll();
		}
	}

}
