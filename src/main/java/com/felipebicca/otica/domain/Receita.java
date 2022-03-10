package com.felipebicca.otica.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Receita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@OneToOne
	@JoinColumn(name = "dioptria_od_id")
	private Dioptria dioptriaDireito;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@OneToOne
	@JoinColumn(name = "dioptria_oe_id")
	private Dioptria dioptriaEsquerdo;

	private Double dp;

	private Double dnpDireito;
	private Double dnpEsquerdo;

	public Receita() {

	}

	public Receita(Integer id, Consulta consulta, Dioptria dioptriaDireito, Dioptria dioptriaEsquerdo, Double dp,
			Double dnpDireito, Double dnpEsquerdo) {
		this.id = id;
		this.consulta = consulta;
		this.dioptriaDireito = dioptriaDireito;
		this.dioptriaEsquerdo = dioptriaEsquerdo;
		this.dp = dp;
		this.dnpDireito = dnpDireito;
		this.dnpEsquerdo = dnpEsquerdo;
	}

	public Receita(Integer id, Consulta consulta, Double dp, Double dnpDireito, Double dnpEsquerdo) {
		this.id = id;
		this.consulta = consulta;
		this.dp = dp;
		this.dnpDireito = dnpDireito;
		this.dnpEsquerdo = dnpEsquerdo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Dioptria getDioptriaDireito() {
		return dioptriaDireito;
	}

	public void setDioptriaDireito(Dioptria dioptriaDireito) {
		this.dioptriaDireito = dioptriaDireito;
	}

	public Dioptria getDioptriaEsquerdo() {
		return dioptriaEsquerdo;
	}

	public void setDioptriaEsquerdo(Dioptria dioptriaEsquerdo) {
		this.dioptriaEsquerdo = dioptriaEsquerdo;
	}

	public Double getDp() {
		return dp;
	}

	public void setDp(Double dp) {
		this.dp = dp;
	}

	public Double getDnpDireito() {
		return dnpDireito;
	}

	public void setDnpDireito(Double dnpDireito) {
		this.dnpDireito = dnpDireito;
	}

	public Double getDnpEsquerdo() {
		return dnpEsquerdo;
	}

	public void setDnpEsquerdo(Double dnpEsquerdo) {
		this.dnpEsquerdo = dnpEsquerdo;
	}

	@Override
	public String toString() {
		return "Receita [id=" + id + ", consulta=" + consulta + ", dioptriaDireito=" + dioptriaDireito
				+ ", dioptriaEsquerdo=" + dioptriaEsquerdo + ", dp=" + dp + ", dnpDireito=" + dnpDireito
				+ ", dnpEsquerdo=" + dnpEsquerdo + "]";
	}
}
