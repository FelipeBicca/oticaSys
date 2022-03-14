package com.felipebicca.otica.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebicca.otica.domain.Consulta;
import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.domain.Receita;
import com.felipebicca.otica.dto.ConsultaNewDTO;
import com.felipebicca.otica.repositories.ConsultaRepository;
import com.felipebicca.otica.repositories.ReceitaRepository;
import com.felipebicca.otica.services.exceptions.ObjectNotFoundException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repo;

	@Autowired
	private ReceitaRepository receitaRepository;

	public Consulta find(Integer id) {
		Optional<Consulta> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Consulta.class.getName(), null));
	}

	public List<Consulta> findAll() {
		return repo.findAll();

	}

	public @Valid Consulta insert(@Valid Consulta obj, List<Receita> receitas) {
		obj.setId(null);

		repo.save(obj);

		for (Receita rec : receitas) {
			rec.setConsulta(obj);
			obj.getReceitas().add(rec);
		}

		receitaRepository.saveAll(obj.getReceitas());

		return obj;
	}

	public Consulta fromDTO(@Valid ConsultaNewDTO obj, Pessoa pessoa) {
		/*
		 * List<Receita> receitas = new ArrayList<Receita>();
		 * 
		 * if (!obj.getReceitas().isEmpty()) { for (Receita rec : obj.getReceitas()) {
		 * receitas.add(rec); } }
		 */
		return new Consulta(null, obj.getAnotacao(), pessoa);
	}
}
