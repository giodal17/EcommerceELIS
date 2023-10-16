package com.elis.service;

import org.springframework.http.ResponseEntity;

import com.elis.dto.request.RigaDOrdineRequest;
import com.elis.model.Categoria;
import com.elis.model.RigaDOrdine;

public interface RigaDOrdineService {

//	public Categoria addRiga(String Righ);
//	public Categoria getById(long id);
//	public boolean deleteById(long id);

	public RigaDOrdine addRigaDOrdine(RigaDOrdineRequest request);
	public RigaDOrdine getById(long id);
	public boolean deleteById(long id);
	public RigaDOrdine riduciQuantità(RigaDOrdineRequest request);
	public RigaDOrdine aggiungiQuantità(RigaDOrdineRequest request);
}
