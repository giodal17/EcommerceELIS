package com.elis.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SottocategoriaRequest {
	@NotBlank(message="La sottocategoria deve esistere")
	private String nomeSottocategoria;
	@NotBlank(message="La categoria deve esistere")
	private String nomeCategoria;
	
	
	public SottocategoriaRequest(@NotBlank(message = "La sottocategoria deve esistere") String nomeSottocategoria,
			@NotBlank(message = "La categoria deve esistere") String nomeCategoria) {
		super();
		this.nomeSottocategoria = nomeSottocategoria;
		this.nomeCategoria = nomeCategoria;
	}
	public String getNomeSottocategoria() {
		return nomeSottocategoria;
	}
	public void setNomeSottocategoria(String nomeSottocategoria) {
		this.nomeSottocategoria = nomeSottocategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
	
}
