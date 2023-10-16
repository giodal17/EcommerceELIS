package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;

public class AddProdottoInTabellaDiRaccordo {
	@Digits(fraction = 0, integer = 10, message= "L'id prodotto deve essere un numero")
	private long idProdotto;
	@Pattern(regexp = "^[a-z][a-z]*(([A-Z][a-z]+)*[A-Z]?|([a-z]+[A-Z])*|[A-Z])$\r\n",
			message = " Deve essere composta slo da caratteri alfabetici")
	private String nomeSottocategoria;
	public long getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getNomeSottocategoria() {
		return nomeSottocategoria;
	}
	public void setNomeSottocategoria(String nomeSottocategoria) {
		this.nomeSottocategoria = nomeSottocategoria;
	}
	public AddProdottoInTabellaDiRaccordo(
			@Digits(fraction = 0, integer = 10, message = "L'id prodotto deve essere un numero") long idProdotto,
			@Pattern(regexp = "^[a-z][a-z]*(([A-Z][a-z]+)*[A-Z]?|([a-z]+[A-Z])*|[A-Z])$\r\n", message = " Deve essere composta slo da caratteri alfabetici") String nomeSottocategoria) {
		super();
		this.idProdotto = idProdotto;
		this.nomeSottocategoria = nomeSottocategoria;
	}
	
	
}
