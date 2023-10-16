package com.elis.dto.request;

import com.elis.model.IVA;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class AddProdottoRequest {
	@NotBlank(message = "Il nome deve esistere")
	private String nome;
	@NotBlank(message = "La descrizione deve esistere")
	private String descrizione;
	
	@Digits(fraction = 2, integer = 5)
	private double prezzo;
	@NotBlank(message = "La marca deve esistere")
	private String marca;
	
	@Digits(fraction = 0, integer = 10)
	private int quantità;
	
	@Digits(fraction = 0, integer = 10)
	private long idVenditore;
	@NotBlank(message = "La sottocategoria deve esistere")
	private String nomeSottocategoria;
	private IVA iva;
	
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
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public long getIdVenditore() {
		return idVenditore;
	}
	public void setIdVenditore(long idVenditore) {
		this.idVenditore = idVenditore;
	}
	
	
	public AddProdottoRequest(@NotBlank(message = "Il nome deve esistere") String nome,
			@NotBlank(message = "La descrizione deve esistere") String descrizione,
			@Digits(fraction = 2, integer = 5) double prezzo,
			@NotBlank(message = "La marca deve esistere") String marca,
			@Digits(fraction = 0, integer = 10) int quantità,
			@Digits(fraction = 0, integer = 10) long idVenditore,
			@NotBlank(message = "La sottocategoria deve esistere") String nomeSottocategoria,
			IVA iva) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.marca = marca;
		this.quantità = quantità;
		this.idVenditore = idVenditore;
		this.nomeSottocategoria = nomeSottocategoria;
		this.iva = iva;
	}
	public IVA getIva() {
		return iva;
	}
	public void setIva(IVA iva) {
		this.iva = iva;
	}
	public String getNomeSottocategoria() {
		return nomeSottocategoria;
	}
	public void setNomeSottocategoria(String nomeSottocategoria) {
		this.nomeSottocategoria = nomeSottocategoria;
	}
	
	
}
