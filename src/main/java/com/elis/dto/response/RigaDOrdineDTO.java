package com.elis.dto.response;


import com.elis.model.RigaDOrdine;

	public class RigaDOrdineDTO {
	private ProdottoDTOPerCarrello prodotto;
	private int quantità;
	public RigaDOrdineDTO(RigaDOrdine riga) {
		super();
		this.prodotto = new ProdottoDTOPerCarrello(riga.getProdotto());
		this.quantità = riga.getQuantita();
	}
	public RigaDOrdineDTO() {
		
	}
	public ProdottoDTOPerCarrello getProdotto() {
		return prodotto;
	}
	public void setProdotto(ProdottoDTOPerCarrello prodotto) {
		this.prodotto = prodotto;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
}
