package com.elis.dto.response;

import java.util.List;

import com.elis.dto.request.SottocategoriaRequest;
import com.elis.model.Prodotto;
import com.elis.model.Sottocategoria;

public class ProdottoDTO {
	
	private long id;
	private String nome;
	private String descrizione;
	private double prezzo;
	private String marca;
	private double quantità;
	private List<SottocategoriaDTO> sottocategorie;
	private long idVenditore;
	private String IVA;
	
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public double getQuantità() {
		return quantità;
	}
	public void setQuantità(double quantità) {
		this.quantità = quantità;
	}
	public long getIdVenditore() {
		return idVenditore;
	}
	public void setIdVenditore(long idVenditore) {
		this.idVenditore = idVenditore;
	}
	public ProdottoDTO(Prodotto p) {
		
		this.id = p.getId();
		this.nome = p.getNome();
		this.descrizione = p.getDescrizione();
		this.prezzo = (p.getPrezzo()*p.getIva().getValore()/100)+p.getPrezzo();
		this.marca = p.getMarca().getNome();
		this.quantità = p.getQuantità();
		this.setSottocategorie(p.getSottocategoria().stream().map(SottocategoriaDTO::new).toList());
		this.idVenditore = p.getIdVenditore();
		this.IVA = p.getIva().toString();
	}
	public ProdottoDTO() {
	
	}
	public List<SottocategoriaDTO> getSottocategorie() {
		return sottocategorie;
	}
	public void setSottocategorie(List<SottocategoriaDTO> sottocategorie) {
		this.sottocategorie = sottocategorie;
	}
	public String getIVA() {
		return IVA;
	}
	public void setIVA(String iVA) {
		IVA = iVA;
	}
	
	
}
