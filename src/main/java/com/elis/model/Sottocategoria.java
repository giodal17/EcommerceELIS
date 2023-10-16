package com.elis.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "categoria_id"})})
public class Sottocategoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false, unique = true)	
	private String nome;

	@ManyToOne
	@JoinColumn(name ="categoria_id", unique = true, nullable = false)
	private Categoria categoria;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable( name = "prodotti_sottocategorie",
	joinColumns = @JoinColumn(name="id_sottocategoria"),
	inverseJoinColumns = @JoinColumn(name="id_prodotto"))
	private List <Prodotto> prodotti;
	
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Prodotto> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	@Override
	public String toString() {
		return "Sottocategoria [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", prodotti=" + prodotti
				+ "]";
	}
	public Sottocategoria(String nome, Categoria categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}
	public Sottocategoria() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(categoria, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sottocategoria other = (Sottocategoria) obj;
		if (categoria == null && other.categoria !=null)return false;
		if (categoria != null && other.categoria ==null)return false;
		if(this.eliminato == false && other.eliminato == false)
			return Objects.equals(categoria, other.categoria) && Objects.equals(nome, other.nome);
		return false;
	}
	
	
	
}
