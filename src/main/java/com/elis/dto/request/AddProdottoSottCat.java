package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class AddProdottoSottCat {
	@NotBlank(message = "L'id deve esistere")
	@Digits(fraction = 0, integer = 5)
	private long id;
	@NotBlank(message = "La sottocategoria deve esistere")
	private String nomeSottocategoria;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeSottocategoria() {
		return nomeSottocategoria;
	}
	public void setNomeSottocategoria(String nomeSottocategoria) {
		this.nomeSottocategoria = nomeSottocategoria;
	}
	public AddProdottoSottCat(@NotBlank(message = "L'id deve esistere") @Digits(fraction = 0, integer = 5) long id,
			@NotBlank(message = "La sottocategoria deve esistere") String nomeSottocategoria) {
		super();
		this.id = id;
		this.nomeSottocategoria = nomeSottocategoria;
	}
	
	
	
}
