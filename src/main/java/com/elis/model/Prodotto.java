package com.elis.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "idVenditore"})})
public class Prodotto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable=false, unique = true)	
	private String nome;
	@Column(nullable=false)	
	private String descrizione;
	@Column(nullable=false)	
	private double prezzo;
	@ManyToOne
	@JoinColumn(name="marca_id")
	private Marca marca;
	@Column(nullable=false)	
	private int quantità;
	@ManyToMany
	@JoinTable( name = "prodotti_sottocategorie",
			joinColumns = @JoinColumn(name="id_prodotto"),
			inverseJoinColumns = @JoinColumn(name="id_sottocategoria"))
	private List<Sottocategoria> sottocategoria;
	@Column(nullable=false, unique = true)	
	private long idVenditore;
	@Column(nullable=false)	
	private IVA iva;
	
	@OneToMany(mappedBy="prodotto")
	private List<RigaDOrdine> righedordini;
	
	@Column(nullable = false)
	private boolean eliminato;

	public boolean isEliminato() {
		return eliminato;
	}
	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}
	
	public Prodotto() {
	}
	
	public Prodotto(String nome, String descrizione, double prezzo, Marca marca, int quantità,
			List<Sottocategoria> sottocategoria, long idVenditore, IVA iva) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.marca = marca;
		this.quantità = quantità;
		this.sottocategoria = sottocategoria;
		this.idVenditore = idVenditore;
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ ", marca=" + marca + ", quantità=" + quantità + ", sottocategoria=" + sottocategoria + ", venditore="
				+ idVenditore + ", iva=" + iva + "]";
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
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	public List<Sottocategoria> getSottocategoria() {
		return sottocategoria;
	}
	public void setSottocategoria(List<Sottocategoria> sottocategoria) {
		this.sottocategoria = sottocategoria;
	}
	public long getIdVenditore() {
		return idVenditore;
	}
	public void setVenditore(long idVenditore) {
		this.idVenditore = idVenditore;
	}
	public IVA getIva() {
		return iva;
	}
	public void setIva(IVA iva) {
		this.iva = iva;
	}
	public List<RigaDOrdine> getRighedordini() {
		return righedordini;
	}
	public void setRighedordini(List<RigaDOrdine> righedordini) {
		this.righedordini = righedordini;
	}
	@Override
	public int hashCode() {
		return Objects.hash(marca, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		if(this.eliminato == false && other.eliminato == false)
		return Objects.equals(marca, other.marca) && Objects.equals(nome, other.nome);
		return false;
	}

	public int addQuantità(int quantità) {
		this.quantità+=quantità;
		return quantità;
	}
	
	public int removeQuantità(int quantità) {
		this.quantità-=quantità;
		return quantità;
	}
	public Prodotto(long id, String nome, String descrizione, double prezzo, Marca marca, int quantità,
			List<Sottocategoria> sottocategoria, long idVenditore, IVA iva) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.marca = marca;
		this.quantità = quantità;
		this.sottocategoria = sottocategoria;
		this.idVenditore = idVenditore;
		this.iva = iva;
	}
	
}
