package com.spring.controller;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.pojo.Persona;
import com.spring.service.PersonaService;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@RequestMapping(value = "/personas", method = RequestMethod.POST, headers = "Accept=application/xml,application/json")
	@ResponseBody
	private String add(@RequestBody Persona objeto) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			personaService.save(objeto);
			return mapper.writeValueAsString(new ResponseEntity<Object>(objeto, HttpStatus.OK));
		} catch (Exception e) {
			System.out.println("Error method:" + e.getMessage());
			return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.SERVICE_UNAVAILABLE));
		}
	}

	@RequestMapping(value = "/personas/{id}", method = RequestMethod.PUT, headers = "Accept=application/xml,application/json")
	@ResponseBody
	private String update(@PathVariable("id") int id, @RequestBody Persona objeto) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("PUT method");
		try {
			objeto.setId(id);
			personaService.update(objeto);
			return mapper.writeValueAsString(new ResponseEntity<Object>(objeto, HttpStatus.OK));
		} catch (Exception e) {
			System.out.println("Error method:" + e.getMessage());
			return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.SERVICE_UNAVAILABLE));
		}
	}

	@RequestMapping(value = "/personas/{id}", method = RequestMethod.DELETE, headers = "Accept=application/xml,application/json")
	@ResponseBody
	private String delete(@PathVariable("id") int id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("DELETE method");
		try {
			personaService.delete(id);
			return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.OK));
		} catch (Exception e) {
			System.out.println("Error method:" + e.getMessage());
			return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.SERVICE_UNAVAILABLE));
		}
	}

	@RequestMapping(value = "/personas", method = RequestMethod.GET)
	@ResponseBody
	private String list(@RequestParam(value = "nombre", required = false) String nombre) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Persona> result;
			result = personaService.findByNombre(nombre);

			if (result != null && result.size() > 0) {
				return mapper.writeValueAsString(result);
			}
		} catch (Exception e) {
			System.out.println("Error method:" + e.getMessage());
			return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.SERVICE_UNAVAILABLE));
		}
		return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/personas/{id}", method = RequestMethod.GET)
	@ResponseBody
	private String get(@PathVariable("id") int id) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("GET method id:" + id);
		try {
			Persona result = personaService.findById(id);
			if (result != null) {
				return mapper.writeValueAsString(result);
			}
		} catch (Exception e) {
			System.out.println("Error method:" + e.getMessage());
			return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.SERVICE_UNAVAILABLE));
		}
		return mapper.writeValueAsString(new ResponseEntity<Object>(HttpStatus.NOT_FOUND));
	}

}

