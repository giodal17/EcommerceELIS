package com.elis.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public class UpdateProdottoRequest {
	@NotBlank(message = "L'id deve esistere")
	private long id;
	@NotBlank(message = "Il nome deve esistere")
	private String nome;
	@NotBlank(message = "La descrizione deve esistere")
	private String descrizione;
	@NotBlank(message = "Il prezzo deve esistere")
	@Digits(fraction = 2, integer = 5)
	private double prezzo;
	@NotBlank(message = "La marca deve esistere")
	private String marca;
	@NotBlank(message = "La quantità deve esistere")
	@Digits(fraction = 0, integer = 10)
	private int quantità;
	@NotBlank(message = "'Id Venditore deve esistere")
	@Digits(fraction = 0, integer = 10)
	private long idVenditore;
	@NotBlank(message = "La sottocategoria deve esistere")
	private String nomeSottocategoria;
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
	public String getNomeSottocategoria() {
		return nomeSottocategoria;
	}
	public void setNomeSottocategoria(String nomeSottocategoria) {
		this.nomeSottocategoria = nomeSottocategoria;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UpdateProdottoRequest(@NotBlank(message = "L'id deve esistere") long id,
			@NotBlank(message = "Il nome deve esistere") String nome,
			@NotBlank(message = "La descrizione deve esistere") String descrizione,
			@NotBlank(message = "Il prezzo deve esistere") @Digits(fraction = 2, integer = 5) double prezzo,
			@NotBlank(message = "La marca deve esistere") String marca,
			@NotBlank(message = "La quantità deve esistere") @Digits(fraction = 0, integer = 10) int quantità,
			@NotBlank(message = "'Id Venditore deve esistere") @Digits(fraction = 0, integer = 10) long idVenditore,
			@NotBlank(message = "La sottocategoria deve esistere") String nomeSottocategoria) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.marca = marca;
		this.quantità = quantità;
		this.idVenditore = idVenditore;
		this.nomeSottocategoria = nomeSottocategoria;
	}
	
	
	
}
