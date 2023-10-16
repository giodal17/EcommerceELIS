package com.elis.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class RigaDOrdine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="prodotto_id", updatable=false)
	private Prodotto prodotto;
	@Column(nullable=false)
	private int quantità;
	@ManyToOne
	@JoinColumn(name="ordine_id", updatable=false)
	private Ordine ordine;
	
	@Column(nullable = false)
	private boolean eliminato;

	public boolean isEliminato() {
		return eliminato;
	}
	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}
	
	public RigaDOrdine(Prodotto prodotto, int quantita, Ordine ordine) {
		this.prodotto = prodotto;
		this.quantità = quantita;
		this.ordine = ordine;
	}
	public RigaDOrdine() {
		super();
	}
	@Override
	public String toString() {
		return "RigaDOrdine [id=" + id + ", prodotto=" + prodotto + ", quantita=" + quantità + ", ordine=" + ordine
				+ "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	public int getQuantita() {
		return quantità;
	}
	public void setQuantita(int quantita) {
		this.quantità = quantita;
	}
	public void diminuisciQuantita(int quantita) {
		this.quantità-=quantita;
	}
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ordine, prodotto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RigaDOrdine other = (RigaDOrdine) obj;
		if(ordine!=null && other.ordine!=null && ordine.getCliente()!= null && other.ordine.getCliente()!=null && this.eliminato == false && other.eliminato == false)
		return Objects.equals(ordine.getCliente(), other.ordine.getCliente()) && Objects.equals(prodotto.getId(), other.prodotto.getId());
		return false;
	}
	
	
	
}
