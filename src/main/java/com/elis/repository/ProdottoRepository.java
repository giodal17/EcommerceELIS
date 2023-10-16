package com.elis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Prodotto;

import jakarta.transaction.Transactional;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long>{
	
	@Transactional
	@Modifying
	@Query("UPDATE Prodotto p SET p.quantità = p.quantità + :quantitàPlus WHERE p.id = :id")
	public int aggiungiQuantità(int quantitàPlus, long id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Prodotto p SET p.quantità = p.quantità - :quantitàMinus WHERE p.id = :id")
	public int riduciQuantità(int quantitàMinus, long id);
	
	public Optional<List<Prodotto>> findAllProdottoByidVenditore(long idVenditore);
	
	@Modifying
	@Transactional
	@Query("UPDATE Prodotto p SET p.eliminato = true WHERE p.id = :id")
	public int eliminaById(long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Prodotto p SET p.eliminato = false WHERE p.id = :id")
	public int rendiVisibileById(long id);
	
	
	
//	@Modifying
//	@Query("UPDATE Prodotto p SET p.sottocategoria = CONCAT(p.sottocategoria, :s) WHERE p.id = :idProdotto")
//	public Prodotto addProdottoInTabellaDiRaccordo(Sottocategoria s, long idProdotto);
	
//	@Query("SELECT p FROM Prodotto p WHERE p.sottocategoria.id = :id")
// 	public List<Prodotto> getProdottiBySottocategoria(String id);
}
