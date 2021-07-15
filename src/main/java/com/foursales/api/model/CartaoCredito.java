package com.foursales.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "card_id")
	private Long id;
	
	@NotNull
	@Column(name = "card_nome")
	private String nome;
	
	@NotNull
	@Column(name = "card_bandeira")
	private String bandeira;
	
	@NotNull
	@Column(name = "card_numero")
	private String numero;
	
	@NotNull
	@Column(name = "card_validade")
	private String validade;
	
	@JoinColumn(name = "cand_id", referencedColumnName = "cand_id")
	@OneToOne
	private Candidato candidato;	
	
	
	public CartaoCredito() {
		candidato = new Candidato();
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}
	
}
