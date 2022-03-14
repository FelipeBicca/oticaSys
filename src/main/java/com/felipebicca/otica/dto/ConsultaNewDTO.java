package com.felipebicca.otica.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.felipebicca.otica.domain.Receita;

public class ConsultaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idPessoa;
	
	private String anotacao;

	private List<Receita> receitas = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	@Override
	public String toString() {
		return "ConsultaNewDTO [id=" + id + ", idPessoa=" + idPessoa + ", anotacao=" + anotacao + ", receitas="
				+ receitas + "]";
	}
	
}
