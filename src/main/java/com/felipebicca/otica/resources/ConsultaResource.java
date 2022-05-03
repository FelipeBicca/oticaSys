package com.felipebicca.otica.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipebicca.otica.domain.Consulta;
import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.dto.ConsultaNewDTO;
import com.felipebicca.otica.dto.PessoaDTO;
import com.felipebicca.otica.services.ConsultaService;
import com.felipebicca.otica.services.PessoaService;

@RestController
@RequestMapping(value = "/Consultas")
public class ConsultaResource {

	@Autowired
	private ConsultaService service;

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Consulta> find(@PathVariable Integer id) {
		Consulta obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Consulta>> findAll() {
		List<Consulta> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ConsultaNewDTO obj) {
		Pessoa pessoa = pessoaService.find(obj.getIdPessoa());
		Consulta cons = service.fromDTO(obj, pessoa);
		cons = service.insert(cons, obj.getReceitas());

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Consulta> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Consulta obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}
}
