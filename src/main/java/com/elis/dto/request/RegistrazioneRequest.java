package com.elis.dto.request;

import com.elis.model.Ruolo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegistrazioneRequest {
	
	@NotBlank(message = "Il nome deve esistere")
	private String nome;
	@NotBlank(message = "Il cognome deve esistere")
	private String cognome;
	@NotBlank(message = "L'email deve esistere")
	@Email(message = "L'email deve contenere una @")
	private String email;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$", 
	message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri")
	private String password;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$", 
	message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri")
	private String passwordConferma;
	private Ruolo ruolo;
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
	public String getPasswordConferma() {
		return passwordConferma;
	}
	public void setPasswordConferma(String passwordConferma) {
		this.passwordConferma = passwordConferma;
	}
	
	public RegistrazioneRequest(@NotBlank(message = "Il nome deve esistere") String nome,
			@NotBlank(message = "Il cognome deve esistere") String cognome,
			@NotBlank(message = "L'email deve esistere") @Email(message = "L'email deve contenere una @") String email,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$", message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri") String password,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$", message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri") String passwordConferma) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.passwordConferma = passwordConferma;
	}
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
