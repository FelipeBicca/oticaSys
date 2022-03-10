package com.felipebicca.otica.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipebicca.otica.domain.Consulta;
import com.felipebicca.otica.services.ConsultaService;

@RestController
@RequestMapping(value = "/Consultas")
public class ConsultaResource {
	
	@Autowired
	private ConsultaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Consulta> find(@PathVariable Integer id) {
		Consulta obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Consulta>> findAll() {
		List<Consulta> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}
}
