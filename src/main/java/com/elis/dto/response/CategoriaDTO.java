package com.elis.dto.response;

import java.util.List;

public class CategoriaDTO {
private long id;
private String nome;
private List<SottocategoriaDTO> sottocategorie;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public List<SottocategoriaDTO> getSottocategorie() {
	return sottocategorie;
}
public void setSottocategorie(List<SottocategoriaDTO> sottocategorie) {
	this.sottocategorie = sottocategorie;
}
public CategoriaDTO() {
	super();
}
public CategoriaDTO(long id, String nome, List<SottocategoriaDTO> sottocategorie, List<ProdottoDTO> prodotti) {
	super();
	this.id = id;
	this.nome = nome;
	this.sottocategorie = sottocategorie;
}


}
