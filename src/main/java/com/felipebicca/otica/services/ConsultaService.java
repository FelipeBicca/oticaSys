package com.felipebicca.otica.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipebicca.otica.domain.Consulta;
import com.felipebicca.otica.repositories.ConsultaRepository;
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

}
