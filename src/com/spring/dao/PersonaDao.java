package com.spring.dao;

import java.util.List;
import com.spring.pojo.Persona;


public interface PersonaDao {
	public void save(Persona objeto);
	public List<Persona>findAll();
	public Persona findById(int id);
	public List<Persona>findByNombre(String nombre);
	public void update(Persona objeto);
	public void delete(Persona objeto);
}
