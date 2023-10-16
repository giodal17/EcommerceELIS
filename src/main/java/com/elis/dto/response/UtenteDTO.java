package com.elis.dto.response;

import java.util.List;

import com.elis.model.Utente;

public class UtenteDTO {
	private long id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private List<IndirizzoDTO> indirizzi;
	private String ruolo;
	private List<OrdineDTO> ordini;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<IndirizzoDTO> getIndirizzi() {
		return indirizzi;
	}
	public void setIndirizzi(List<IndirizzoDTO> indirizzi) {
		this.indirizzi = indirizzi;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public List<OrdineDTO> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<OrdineDTO> ordini) {
		this.ordini = ordini;
	}
	public UtenteDTO(Utente u) {
		super();
		this.id = u.getId();
		this.nome = u.getNome();
		this.cognome = u.getCognome();
		this.email = u.getEmail();
		this.password = u.getPassword();
		this.indirizzi = u.getIndirizzi().stream().map(IndirizzoDTO ::new).toList();
		this.ruolo = u.getRuolo().getRuolo();
		this.ordini = u.getOrdini().stream().map(OrdineDTO :: new).toList();
	}
	public UtenteDTO() {
	}
	
	
}
