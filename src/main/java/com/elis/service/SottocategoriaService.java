package com.elis.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.elis.dto.request.SottocategoriaRequest;
import com.elis.dto.response.ProdottoDTO;
import com.elis.model.Prodotto;
import com.elis.model.Sottocategoria;

public interface SottocategoriaService {
	
	public Sottocategoria getById(long id);
	public Sottocategoria addSottocategoria(SottocategoriaRequest request);
	public boolean deleteById(long id);
	public Sottocategoria update(SottocategoriaRequest request);
	public List<ProdottoDTO> getProdottiBySottocategoria(String nome);
	

}
