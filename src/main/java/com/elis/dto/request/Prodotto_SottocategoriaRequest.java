package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class Prodotto_SottocategoriaRequest {
	@NotBlank(message = "L'id Categoria deve esistere")
	@Digits(fraction = 0, integer = 5)
	private long idCategoria;
	@NotBlank(message = "L'id Prodotto deve esistere")
	@Digits(fraction = 0, integer = 5)
	private long idProdotto;
	public long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public long getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}
	public Prodotto_SottocategoriaRequest(
			@NotBlank(message = "L'id Categoria deve esistere") @Digits(fraction = 0, integer = 5) long idCategoria,
			@NotBlank(message = "L'id Prodotto deve esistere") @Digits(fraction = 0, integer = 5) long idProdotto) {
		super();
		this.idCategoria = idCategoria;
		this.idProdotto = idProdotto;
	}
	
	
	
}
