package com.elis.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Ordine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany(mappedBy = "ordine")
	private List<RigaDOrdine> righe;
	@Column(nullable=true)
	private LocalDate dataInvioOrdine;
	
	@ManyToOne
	@JoinColumn(name="utente_id")
	private Utente cliente;
	
	@Column(nullable=false)
	private double prezzoTotaleIvato;

	@Column(nullable = false)
	private boolean eliminato;

	public boolean isEliminato() {
		return eliminato;
	}
	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}
	
	public Ordine(Utente cliente) {
		super();
		this.cliente = cliente;
		this.prezzoTotaleIvato = 0;
	}

	public Ordine() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public List<RigaDOrdine> getRighe() {
		return righe;
	}

	public void setRighe(List<RigaDOrdine> righe) {
		this.righe = righe;
	}

	public LocalDate getDataInvioOrdine() {
		return dataInvioOrdine;
	}

	public void setDataInvioOrdine(LocalDate dataInvioOrdine) {
		this.dataInvioOrdine = dataInvioOrdine;
	}

	public Utente getCliente() {
		return cliente;
	}

	public void setCliente(Utente cliente) {
		this.cliente = cliente;
	}

	public double getPrezzoTotaleIvato() {
		return prezzoTotaleIvato;
	}

	public void setPrezzoTotaleIvato(double prezzoTotaleIvato) {
		this.prezzoTotaleIvato = prezzoTotaleIvato;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		if (dataInvioOrdine == null && other.dataInvioOrdine != null)
			return false;
		if (dataInvioOrdine != null && other.dataInvioOrdine == null)
			return false;
		if(this.eliminato == false && other.eliminato == false)
			return Objects.equals(cliente, other.cliente) && dataInvioOrdine != null
				&& dataInvioOrdine.equals(other.dataInvioOrdine);
			return false;
		
	}
	
	public LocalDate confermaOrdine() {
		this.dataInvioOrdine = LocalDate.now();
		return LocalDate.now();
	}
}
