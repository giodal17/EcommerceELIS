package com.elis.dto.response;

import com.elis.model.Indirizzo;

public class IndirizzoDTO {
	
	private long id;
	private String indirizzo1;
	private String provincia;
	private String cap;
	private String numeroTelefono;
	private String comune;
	private String nome;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIndirizzo1() {
		return indirizzo1;
	}
	public void setIndirizzo1(String indirizzo1) {
		this.indirizzo1 = indirizzo1;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public IndirizzoDTO() {
		super();
	}
	public IndirizzoDTO(Indirizzo i) {
		super();
		this.id = i.getId();
		this.indirizzo1 = i.getIndirizzo1();
		this.provincia = i.getProvincia();
		this.cap = i.getCap();
		this.numeroTelefono = i.getNumeroTelefono();
		this.comune = i.getComune();
		this.nome = i.getNome();
	}

}
