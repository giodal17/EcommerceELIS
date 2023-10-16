package com.elis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class modificaPswRequest {
	@Email(message = "Deve contenere una @")
	private String email;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", 
			message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri")
	private String passwordVecchia;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", 
			message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri")
	private String passwordNuova;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", 
			message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri")
	private String passwordConferma;
	
	public String getPasswordVecchia() {
		return passwordVecchia;
	}
	public void setPasswordVecchia(String passwordVecchia) {
		this.passwordVecchia = passwordVecchia;
	}
	public String getPasswordNuova() {
		return passwordNuova;
	}
	public void setPasswordNuova(String passwordNuova) {
		this.passwordNuova = passwordNuova;
	}
	public String getPasswordConferma() {
		return passwordConferma;
	}
	public void setPasswordConferma(String passwordConferma) {
		this.passwordConferma = passwordConferma;
	}
	
	public modificaPswRequest(@Email(message = "Deve contenere una @") String email,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri") String passwordVecchia,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri") String passwordNuova,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri") String passwordConferma) {
		super();
		this.email = email;
		this.passwordVecchia = passwordVecchia;
		this.passwordNuova = passwordNuova;
		this.passwordConferma = passwordConferma;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
