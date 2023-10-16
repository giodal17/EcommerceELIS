package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class UpdateIndirizzoRequest {
	@NotBlank(message = "L'id Indirizzo deve esistere")
	@Digits(fraction = 0, integer = 5)
	private long idIndirizzo;
	@NotBlank(message = "L'id Utente deve esistere")
	@Digits(fraction = 0, integer = 5)
	private long idUtente;
	@NotBlank(message = "L'indirizzo deve esistere")
	private String indirizzo1;
	@NotBlank(message = "La provincia deve esistere")
	private String provincia;
	@NotBlank(message = "Il cap deve esistere")
	private String cap;
	@NotBlank(message = "Il numero di telefono deve esistere")
	private String numeroTelefono;
	@NotBlank(message = "Il comune deve esistere")
	private String comune;
	@NotBlank(message = "Il nome dell'abitazione deve esistere")
	private String nome;
	public long getIdIndirizzo() {
		return idIndirizzo;
	}
	public void setIdIndirizzo(long idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(long idUtente) {
		this.idUtente = idUtente;
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
	public UpdateIndirizzoRequest(
			@NotBlank(message = "L'id Indirizzo deve esistere") @Digits(fraction = 0, integer = 5) long idIndirizzo,
			@NotBlank(message = "L'id Utente deve esistere") @Digits(fraction = 0, integer = 5) long idUtente,
			@NotBlank(message = "L'indirizzo deve esistere") String indirizzo1,
			@NotBlank(message = "La provincia deve esistere") String provincia,
			@NotBlank(message = "Il cap deve esistere") String cap,
			@NotBlank(message = "Il numero di telefono deve esistere") String numeroTelefono,
			@NotBlank(message = "Il comune deve esistere") String comune,
			@NotBlank(message = "Il nome dell'abitazione deve esistere") String nome) {
		super();
		this.idIndirizzo = idIndirizzo;
		this.idUtente = idUtente;
		this.indirizzo1 = indirizzo1;
		this.provincia = provincia;
		this.cap = cap;
		this.numeroTelefono = numeroTelefono;
		this.comune = comune;
		this.nome = nome;
	}
	
	
}
