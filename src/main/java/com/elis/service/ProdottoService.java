package com.elis.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.elis.dto.request.AddProdottoRequest;
import com.elis.dto.request.EliminazioneProdottoRequest;
import com.elis.dto.request.ProdottoQuantitàRequest;
import com.elis.dto.request.UpdateProdottoRequest;
import com.elis.model.Prodotto;

public interface ProdottoService {
	public Prodotto getById(long id);
	public Prodotto addProdotto(AddProdottoRequest request);
	public Prodotto aggiungiQuantità(ProdottoQuantitàRequest request);
	public Prodotto riduciQuantità(ProdottoQuantitàRequest request);
	public String removeById(EliminazioneProdottoRequest request);
	public Prodotto updateProdotto(UpdateProdottoRequest prodotto);
	public List<Prodotto> getAllProdottiByVenditore(long idVenditore);
	public Prodotto rendiVisibile(EliminazioneProdottoRequest request);
	public List<Prodotto> findAll();
}
