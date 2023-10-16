package com.elis.service;

import org.springframework.http.ResponseEntity;

import com.elis.model.Categoria;

public interface CategoriaService {
	public Categoria addCategoria(String categoria);
	public Categoria getById(long id);
	public boolean deleteById(long id);

}
