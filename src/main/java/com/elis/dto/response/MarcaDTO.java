package com.elis.dto.response;

import java.util.List;

public class MarcaDTO {
	private long id;
	private String nome;
	private List<ProdottoDTO> prodotti;
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
	public List<ProdottoDTO> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<ProdottoDTO> prodotti) {
		this.prodotti = prodotti;
	}
//	public MarcaDTO(long id, String nome, List<ProdottoDTO> prodotti) {
//		super();
//		this.id = id;
//		this.nome = nome;
//		this.prodotti = prodotti;
//	}
	public MarcaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
}
