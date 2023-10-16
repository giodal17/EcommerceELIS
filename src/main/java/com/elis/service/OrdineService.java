package com.elis.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.elis.model.Ordine;

public interface OrdineService {
	
	public Ordine addOrdine(long idCliente);
	public ResponseEntity<String> removeById(long id);
	public Ordine getById(long id);
	public Ordine getCarrello(String email);
	public Ordine confermaAcquisto(long id);
	public List<Ordine> getOrdiniConfermati(String email);
	public ResponseEntity<String> updatePrezzoTotale(long id, double prezzo);

}
