package com.felipebicca.otica.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.felipebicca.otica.domain.Pessoa;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 200")
	private String nome;

	//@NotEmpty(message = "Preenchimento obrigatório")
	//@Email(message = "E-mail inválido")
	private Set<String> emails;

	public PessoaDTO() {
	}
	
	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.emails = pessoa.getEmails();
	}


	public PessoaDTO(Integer id, String nome, Set<String> email) {
		this.id = id;
		this.nome = nome;
		this.emails = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> email) {
		this.emails = email;
	}

	@Override
	public String toString() {
		return "PessoaDTO [id=" + id + ", nome=" + nome + ", email=" + emails + "]";
	}
}
