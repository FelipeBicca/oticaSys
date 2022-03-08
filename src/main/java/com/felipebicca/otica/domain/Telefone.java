package com.felipebicca.otica.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer ddd;
	
	private String numero;
	
	private boolean ehWhats;
	
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	public Telefone() {
	}
	
	public Telefone(Integer id, Integer ddd, String numero, boolean ehWhats, Pessoa pessoa) {
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.ehWhats = ehWhats;
		this.pessoa = pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isEhWhats() {
		return ehWhats;
	}

	public void setEhWhats(boolean ehWhats) {
		this.ehWhats = ehWhats;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", ehWhats=" + ehWhats + ", pessoa="
				+ pessoa + "]";
	}
}
