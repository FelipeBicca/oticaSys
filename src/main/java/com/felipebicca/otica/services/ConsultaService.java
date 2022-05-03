package com.felipebicca.otica.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipebicca.otica.domain.Consulta;
import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.domain.Receita;
import com.felipebicca.otica.dto.ConsultaNewDTO;
import com.felipebicca.otica.repositories.ConsultaRepository;
import com.felipebicca.otica.services.exceptions.DataIntegrityException;
import com.felipebicca.otica.services.exceptions.ObjectNotFoundException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repo;

	public Consulta find(Integer id) {
		Optional<Consulta> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Consulta.class.getName(), null));
	}

	public List<Consulta> findAll() {
		return repo.findAll();

	}

	@Transactional
	public @Valid Consulta insert(@Valid Consulta obj, List<Receita> receitas) {
		obj.setId(null);

		for (Receita rec : receitas) {
			rec.setConsulta(obj);
			obj.getReceitas().add(rec);
		}

		repo.save(obj);

		return obj;
	}

	public Consulta fromDTO(@Valid ConsultaNewDTO obj, Pessoa pessoa) {
		return new Consulta(null, obj.getAnotacao(), pessoa);
	}

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há consultas relacionados.");
		}
	}

	public @Valid Consulta update(@Valid Consulta obj) {
		Consulta cons = find(obj.getId());
		return repo.save(cons);
	}
}
