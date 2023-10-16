package com.elis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.elis.database.SingletonProdotto;
import com.elis.dto.request.SottocategoriaRequest;
import com.elis.dto.response.ProdottoDTO;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Categoria;
import com.elis.model.Prodotto;
import com.elis.model.Sottocategoria;
import com.elis.repository.CategoriaRepository;
import com.elis.repository.SottocategoriaRepository;
import com.elis.service.SottocategoriaService;

@Service
public class SottocategoriaServiceImpl implements SottocategoriaService{
	
private static SottocategoriaServiceImpl istance = new SottocategoriaServiceImpl();
	
	private SottocategoriaServiceImpl() {}
	
	public static SottocategoriaServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	SottocategoriaRepository repo;
	
	@Autowired
	CategoriaRepository cateRepo;

	@Override
	public Sottocategoria getById(long id) {
		try {
			return repo.findById(id).orElse(null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Sottocategoria addSottocategoria(SottocategoriaRequest request) {
//		try {
			Categoria c = cateRepo.findByNome(request.getNomeCategoria());
			Sottocategoria s = new Sottocategoria(request.getNomeSottocategoria(), c);
			return repo.save(s);
//			return ResponseEntity.ok().body("Sottocategoria aggiunta!");
//		}catch(DataIntegrityViolationException e) {
//			
//			return ResponseEntity.badRequest().body("Sottocategoria NON aggiunta!");
//		}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
	}

	@Override
	public boolean deleteById(long id) {
//		try {
//			repo.deleteById(id);
//			return true;
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		return false;
	}

	@Override
	public Sottocategoria update(SottocategoriaRequest request) {
		
			Categoria c = cateRepo.findByNome(request.getNomeCategoria());
			Sottocategoria s = new Sottocategoria(request.getNomeSottocategoria(), c);
			return repo.save(s);
		
	}

	@Override
	public List<ProdottoDTO> getProdottiBySottocategoria(String nome) {
		
			List<Prodotto> prodotti = repo.getProdottiByNomeSottocategoria(nome).orElseThrow(() -> new OggettoNonTrovatoException(nome, "Nessun prodotto"));
			return prodotti.stream().filter(p -> p.isEliminato()==false).map(ProdottoDTO ::new).toList();

	}
}
