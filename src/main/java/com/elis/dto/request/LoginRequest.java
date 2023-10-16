package com.elis.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginRequest {
	@NotBlank(message = "L'email deve esistere")
	@Email(message = "Deve contenere una @")
	private String email;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$", 
			message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri")
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LoginRequest(
			@NotBlank(message = "L'email deve esistere") @Email(message = "Deve contenere una @") String email,
			@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$", message = "la password deve rispettare una maiuscola, una minuscola, un numero, un carattere speciale ed essere tra gli 8 e i 20 caratteri") String password) {
		this.email = email;
		this.password = password;
	}
	
	
	

}
