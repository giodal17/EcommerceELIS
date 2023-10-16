package com.elis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Prodotto;
import com.elis.model.Sottocategoria;

import jakarta.transaction.Transactional;

public interface SottocategoriaRepository extends JpaRepository<Sottocategoria, Long>{

	@Query("SELECT s FROM Sottocategoria s WHERE s.nome = :nome")
	public Sottocategoria findByNome(String nome);
	
	
//	@Modifying
//	@Query("UPDATE Sottocategoria s SET s.prodotti = CONCAT(s.prodotti, :p) WHERE s.id = :idSottocategoria")
//	public Sottocategoria addSottocategoriaInTabella(Prodotto p, long idSottocategoria);
	
	@Query("SELECT s.prodotti FROM Sottocategoria s WHERE s.nome = :nome")
	public Optional<List<Prodotto>> getProdottiByNomeSottocategoria (String nome);
	
	@Modifying
	@Transactional
	@Query("UPDATE Sottocategoria s SET s.eliminato = true WHERE s.id = :id")
	public int eliminaById(long id);
	
}
