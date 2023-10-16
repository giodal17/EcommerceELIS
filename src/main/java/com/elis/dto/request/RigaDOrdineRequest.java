package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RigaDOrdineRequest {
	
	@Digits(fraction = 0, integer = 5)
	@Min(value = 1, message = "Immettere una quantità valida")
	private int quantità;
	
	@Digits(fraction = 0, integer = 5)
	private long IdOrdine;
	
	@Digits(fraction = 0, integer = 5)
	private long IdProdotto;
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public long getIdOrdine() {
		return IdOrdine;
	}
	public void setIdOrdine(long idOrdine) {
		IdOrdine = idOrdine;
	}
	public long getIdProdotto() {
		return IdProdotto;
	}
	public void setIdProdotto(long idProdotto) {
		IdProdotto = idProdotto;
	}
	public RigaDOrdineRequest(
			 @Digits(fraction = 0, integer = 5) @Min(value = 1, message = "Immettere una quantità valida") int quantità,
			 @Digits(fraction = 0, integer = 5) long idOrdine,
			 @Digits(fraction = 0, integer = 5) long idProdotto) {
		super();
		this.quantità = quantità;
		IdOrdine = idOrdine;
		IdProdotto = idProdotto;
	}
	
	
	
}
