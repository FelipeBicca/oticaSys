package com.felipebicca.otica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.repositories.PessoaRepository;
import com.felipebicca.otica.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repo;
	
	public Pessoa find(Integer id) {
		Optional<Pessoa> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName(), null));
	}
	
	public List<Pessoa> findAll() {
		return repo.findAll();
	}

}
