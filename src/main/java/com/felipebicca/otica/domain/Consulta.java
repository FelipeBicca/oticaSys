package com.felipebicca.otica.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date dataConsulta = new Date();

	private String anotacao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
	private List<Receita> receitas = new ArrayList<>();

	public Consulta() {
	}

	public Consulta(Integer id, String anotacao, Pessoa pessoa) {
		this.id = id;
		this.anotacao = anotacao;
		this.pessoa = pessoa;
	}

	public Consulta(Integer id, String anotacao, Pessoa pessoa, List<Receita> receitas) {
		this.id = id;
		this.anotacao = anotacao;
		this.pessoa = pessoa;
		this.receitas = receitas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", dataConsulta=" + dataConsulta + ", anotacao=" + anotacao + ", pessoa=" + pessoa
				+ "]";
	}
}
