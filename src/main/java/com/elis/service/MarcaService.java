package com.elis.service;

import org.springframework.http.ResponseEntity;

import com.elis.model.Marca;

public interface MarcaService {
	public ResponseEntity<String> addMarca(String marca);
	public Marca getById(long id);
	public ResponseEntity<String> deleteMarcaById(long id);
	public Marca getByNome(String nome);

}
