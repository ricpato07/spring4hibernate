package com.spring.service;

import java.util.List;
import com.spring.pojo.Persona;


public interface PersonaService {
	public void save(Persona objeto);
	public List<Persona>findAll();
	public Persona findById(int id);
	public List<Persona>findByNombre(String nombre);
	public void update(Persona objeto);
	public void delete(int id);
}
