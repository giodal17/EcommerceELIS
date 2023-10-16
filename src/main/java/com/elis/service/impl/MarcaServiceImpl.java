package com.elis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elis.database.SingletonProdotto;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Marca;
import com.elis.repository.MarcaRepository;
import com.elis.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService{
	
private static MarcaServiceImpl istance = new MarcaServiceImpl();
	
	private MarcaServiceImpl() {}
	
	public static MarcaServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	MarcaRepository repo;

	@Override
	public ResponseEntity<String> addMarca(String marca) {
		
			Marca marcaDaAggiungere = new Marca(marca);
			Marca m = repo.save(marcaDaAggiungere);
			if (m!=null) return ResponseEntity.ok("Marca aggiunta!");
			return ResponseEntity.badRequest().body("Marca NON aggiunta");
			
	}

	@Override
	public Marca getById(long id) {
			Marca marca = repo.findById(id).orElse(null);
			if(marca.isEliminato()==true)return null;
			return marca;
	}

	@Override
	public ResponseEntity<String> deleteMarcaById(long id) {
			if(repo.eliminaById(id)>0) return ResponseEntity.ok("Marca eliminata!");
			return ResponseEntity.badRequest().body("Marca NON eliminata");
	}

	@Override
	public Marca getByNome(String nome) {

		
			Marca m = repo.findMarcaByNome(nome).orElseThrow(() -> new OggettoNonTrovatoException(nome, "Marca NON trovata"));
			return m;
		
		
	}
}
