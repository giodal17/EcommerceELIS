package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AcquistaProdottoRequest {
	@NotBlank(message = "L'email deve esistere")
	@Email(message = "l'email deve essere un'email")
	private String email;
	
	//@NotBlank(message = "L'id Prodotto deve esistere")
	@Digits(fraction = 0, integer = 10, message= "L'id prodotto deve essere un numero")
	@Min(1)
	private long idProdotto;
	
	//@NotBlank(message = "La quantità deve esistere")
	@Digits(fraction = 0, integer = 4, message = "La quantità deve essere un numero")
	@Min(1)
	private int quantità;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public AcquistaProdottoRequest(@Email(message = "lo username deve essere un'email") String email,
			@Digits(fraction = 0, integer = 10, message = "L'id prodotto deve essere un numero") long idProdotto,
			@Digits(fraction = 0, integer = 4, message = "La quantità deve essere un numero") int quantità) {
		super();
		this.email = email;
		this.idProdotto = idProdotto;
		this.quantità = quantità;
	}
	

}
