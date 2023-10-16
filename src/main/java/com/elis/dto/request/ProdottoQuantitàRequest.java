package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProdottoQuantitàRequest {
	
	@Digits(fraction = 0, integer = 5)
	@Min(value = 1, message = "Immettere una quantità valida")
	private int quantità;
	
	@Digits(fraction = 0, integer = 5)
	private long idProdotto;
	
	@Digits(fraction = 0, integer = 5)
	private long idVenditore;
	
	public long getIdVenditore() {
		return idVenditore;
	}
	public void setIdVenditore(long idVenditore) {
		this.idVenditore = idVenditore;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public long getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}
	public ProdottoQuantitàRequest(
			@Digits(fraction = 0, integer = 5) @Min(value = 1, message = "Immettere una quantità valida") int quantità,
			@Digits(fraction = 0, integer = 5) long idProdotto, @Digits(fraction = 0, integer = 5) long idVenditore) {
		super();
		this.quantità = quantità;
		this.idProdotto = idProdotto;
		this.idVenditore = idVenditore;
	}
	public ProdottoQuantitàRequest(
			@Digits(fraction = 0, integer = 5) @Min(value = 1, message = "Immettere una quantità valida") int quantità,
			@Digits(fraction = 0, integer = 5) long idProdotto) {
		super();
		this.quantità = quantità;
		this.idProdotto = idProdotto;
	}
	
	
	
	
}
