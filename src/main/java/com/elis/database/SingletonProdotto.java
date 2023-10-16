package com.elis.database;

import java.util.ArrayList;
import java.util.List;

import com.elis.model.Prodotto;

public class SingletonProdotto {
	
	private static SingletonProdotto istance = new SingletonProdotto();
	
	private SingletonProdotto() {}
	
	public static SingletonProdotto getIstance() {
		return istance;
	}
	
	private List<Prodotto> prodotti = new ArrayList<Prodotto>();
	
	public List <Prodotto> getProdottoByMarca(String marca) {
		return prodotti.stream().filter(p -> p.getMarca().equals(marca)).toList();
	}
	
	public List <Prodotto> getProdottoBySottoCategoria(String sottocategoria) {
		return prodotti.stream().filter(p -> p.getSottocategoria().stream().anyMatch(s -> s.getNome().equalsIgnoreCase(sottocategoria))).toList();
	}
	
	public Prodotto getProdottoById(int id) {
		return prodotti.stream().filter(p -> p.getId()==id).findFirst().orElse(null);   
	}
	
	public boolean aggiungiProdottoNuovo(Prodotto prodotto) {
		try {
			if(!prodotti.stream().anyMatch(p -> p.equals(prodotto))) {
			prodotti.add(prodotto);
			return true;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean aggiungiQuantità(int id, int quantità) {
		
		int ritorno = getProdottoById(id).addQuantità(quantità);
		return ritorno>=quantità? true: false;
	}
	
	public boolean rimuoviQuantità(int id, int quantità) {
		Prodotto prodottoTrovato=getProdottoById(id);
		if(prodottoTrovato!=null && quantità>=prodottoTrovato.getQuantità()) {
			prodottoTrovato.removeQuantità(quantità);
			return true;
		}
		return false;
	}
	
	public boolean aggiornaPrezzo(int id, double prezzo) {
		try {
			Prodotto prodottoTrovato=getProdottoById(id);
		prodottoTrovato.setPrezzo(prezzo);
		return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	

}
