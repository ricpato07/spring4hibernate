package com.spring.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.spring.pojo.Persona;

@Transactional
@Repository
public class PersonaDaoImpl implements PersonaDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Persona objeto) {
		getSession().save(objeto);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findAll() {
		Query query = getSession().createQuery("from Persona");
		return query.list();
	}

	public Persona findById(int id) {
		return (Persona) getSession().get(Persona.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> findByNombre(String nombre) {
		Criteria crit = getSession().createCriteria(Persona.class);
		crit.add(Restrictions.like("nombre", "%" + nombre + "%"));
		return crit.list();
	}

	public void update(Persona objeto) {
		getSession().update(objeto);
	}

	public void delete(Persona objeto) {
		getSession().delete(objeto);
	}

}
