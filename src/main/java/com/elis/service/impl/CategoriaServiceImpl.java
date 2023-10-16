package com.elis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elis.database.SingletonProdotto;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Categoria;
import com.elis.repository.CategoriaRepository;
import com.elis.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private static CategoriaServiceImpl istance = new CategoriaServiceImpl();

	private CategoriaServiceImpl() {
	}

	public static CategoriaServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	CategoriaRepository repo;

	@Override
	public Categoria addCategoria(String categoria) {

		Categoria newCategoria = new Categoria(categoria);
		return repo.save(newCategoria);
//		if (catSalvata != null) {
//			return ResponseEntity.ok("Categoria aggiunta");
//		}
//		return ResponseEntity.badRequest().body("Categoria non aggiunta");
	}

	@Override
	public Categoria getById(long id) {
		if (repo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "Categoria non trovata"))
				.isEliminato())
			throw new OggettoNonTrovatoException(id, "Questa categoria è stata eliminata in precedenza!");
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean deleteById(long id) {

		if (repo.eliminaById(id) > 0)
			return true;
		return false;

	}
}
