package com.elis.dto.response;

import com.elis.model.Sottocategoria;

public class SottocategoriaDTO {
	
	private String nomeSottocategoria;
	private String nomeCategoria;
	
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

	public SottocategoriaDTO(Sottocategoria s) {
		this.nomeCategoria = s.getCategoria().getNome();
		this.nomeSottocategoria = s.getNome();
	}
	public SottocategoriaDTO() {
		// TODO Auto-generated constructor stub
	}
}
