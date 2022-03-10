package com.felipebicca.otica.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dioptria implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Double esferico;
	private Double cilindro;
	private Integer eixo;
	private Double adicao;
	private String observacao;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "receita_id")
	private Receita receita;
	
	public Dioptria() {
	}
	
	public Dioptria(Integer id, Double esferico, Double cilindro, Integer eixo, Double adicao, String observacao, Receita rec) {
		this.id = id;
		this.esferico = esferico;
		this.cilindro = cilindro;
		this.eixo = eixo;
		this.adicao = adicao;
		this.observacao = observacao;
		this.receita = rec;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getEsferico() {
		return esferico;
	}

	public void setEsferico(Double esferico) {
		this.esferico = esferico;
	}

	public Double getCilindro() {
		return cilindro;
	}

	public void setCilindro(Double cilindro) {
		this.cilindro = cilindro;
	}

	public Integer getEixo() {
		return eixo;
	}

	public void setEixo(Integer eixo) {
		this.eixo = eixo;
	}

	public Double getAdicao() {
		return adicao;
	}

	public void setAdicao(Double adicao) {
		this.adicao = adicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	@Override
	public String toString() {
		return "Dioptria [id=" + id + ", esferico=" + esferico + ", cilindro=" + cilindro + ", eixo=" + eixo
				+ ", adicao=" + adicao + ", observacao=" + observacao + "]";
	}
}
