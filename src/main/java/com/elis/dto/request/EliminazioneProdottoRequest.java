package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class EliminazioneProdottoRequest {
	
	@Digits(fraction = 0, integer = 5)
	private long idProdotto;
	
	@Digits(fraction = 0, integer = 5)
	private long idVenditore;
	
	
	public EliminazioneProdottoRequest(
			 @Digits(fraction = 0, integer = 5) long idProdotto,
			 @Digits(fraction = 0, integer = 5) long idVenditore) {
		super();
		this.idProdotto = idProdotto;
		this.idVenditore = idVenditore;
	}
	public long getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}
	public long getIdVenditore() {
		return idVenditore;
	}
	public void setIdVenditore(long idVenditore) {
		this.idVenditore = idVenditore;
	}
	public EliminazioneProdottoRequest(@Digits(fraction = 0, integer = 5) long idProdotto) {
		super();
		this.idProdotto = idProdotto;
	}

	
}
