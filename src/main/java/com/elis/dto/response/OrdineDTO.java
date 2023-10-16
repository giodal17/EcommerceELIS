package com.elis.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.elis.model.Ordine;

public class OrdineDTO {
	private long id;
	private List<RigaDOrdineDTO> righe = new ArrayList<>();
	private LocalDate dataInvioOrdine;
	private double prezzoTotaleIvato;
	public List<RigaDOrdineDTO> getRighe() {
		return righe;
	}
	public void setRighe(List<RigaDOrdineDTO> righe) {
		this.righe = righe;
	}
	public LocalDate getDataInvioOrdine() {
		return dataInvioOrdine;
	}
	public void setDataInvioOrdine(LocalDate dataInvioOrdine) {
		this.dataInvioOrdine = dataInvioOrdine;
	}
	public double getPrezzoTotaleIvato() {
		return prezzoTotaleIvato;
	}
	public void setPrezzoTotaleIvato(double prezzoTotaleIvato) {
		this.prezzoTotaleIvato = prezzoTotaleIvato;
	}
	public OrdineDTO(Ordine o) {
		this.id=o.getId();
		righe = o.getRighe().stream().map(RigaDOrdineDTO::new).toList();
		this.dataInvioOrdine = o.getDataInvioOrdine();
		this.prezzoTotaleIvato = o.getPrezzoTotaleIvato();
	}
	public OrdineDTO() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
