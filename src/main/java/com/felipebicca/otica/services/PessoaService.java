package com.felipebicca.otica.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipebicca.otica.domain.Endereco;
import com.felipebicca.otica.domain.Pessoa;
import com.felipebicca.otica.domain.Telefone;
import com.felipebicca.otica.dto.PessoaDTO;
import com.felipebicca.otica.dto.PessoaNewDTO;
import com.felipebicca.otica.repositories.PessoaRepository;
import com.felipebicca.otica.services.exceptions.DataIntegrityException;
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

	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}

	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	private void updateData(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmails(obj.getEmails());
	}

	public Pessoa fromDTO(PessoaDTO obj) {
		Pessoa pessoa = new Pessoa(obj.getId(), obj.getNome(), null, null, null, null, null);

		for (String email : obj.getEmails()) {
			pessoa.getEmails().add(email);
		}

		return pessoa;
	}

	public Pessoa fromDTO(PessoaNewDTO obj) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date auxDate = new Date();
		try {
			auxDate = formato.parse(obj.getDataNascimento());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Pessoa pessoa = new Pessoa(null, obj.getNome(), obj.getCpf(), obj.getRg(), obj.getSexo(), obj.getEstadoCivil(),
				auxDate);

		for (Endereco endereco : obj.getEnderecos()) {
			endereco.setPessoa(pessoa);
			pessoa.getEnderecos().add(endereco);
		}

		for (Telefone telefone : obj.getTelefones()) {
			telefone.setPessoa(pessoa);
			pessoa.getTelefones().add(telefone);
		}

		for (String email : obj.getEmails()) {
			pessoa.getEmails().add(email);
		}

		return pessoa;
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public void delete(Integer id) {	
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há consultas relacionados.");
		}
	}
}
