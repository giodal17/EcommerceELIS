package com.elis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String nome;
	@OneToMany(mappedBy = "categoria")
	private List <Sottocategoria> sottocategorie = new ArrayList<Sottocategoria>();
	@Column(nullable = false)
	private boolean eliminato;
	
	public boolean isEliminato() {
		return eliminato;
	}
	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}
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
	public List<Sottocategoria> getSottocategorie() {
		return sottocategorie;
	}
	public void setSottocategorie(List<Sottocategoria> sottocategorie) {
		this.sottocategorie = sottocategorie;
	}
	public Categoria(String nome) {
		this.nome = nome;
	}
	public Categoria() {
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", sottocategorie=" + sottocategorie + "]";
	}
	
	
	
}
