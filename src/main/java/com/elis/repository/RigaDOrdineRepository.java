package com.elis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.RigaDOrdine;

import jakarta.transaction.Transactional;

public interface RigaDOrdineRepository extends JpaRepository<RigaDOrdine, Long> {
	
	@Transactional
	@Modifying
	@Query("UPDATE RigaDOrdine r SET r.quantità = r.quantità + :quantità WHERE r.id = :id")
	public int aggiungiQuantità(long id, int quantità);
	
	@Transactional
	@Modifying
	@Query("UPDATE RigaDOrdine r SET r.quantità = r.quantità - :quantità WHERE r.id = :id")
	public int rimuoviQuantità(long id, int quantità);
	
	@Modifying
	@Transactional
	@Query("UPDATE RigaDOrdine r SET r.eliminato = true WHERE r.id = :id")
	public int eliminaById(long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE RigaDOrdine r SET r.eliminato = true WHERE r.prodotto.id = :id AND r.ordine.id = :id_ordine")
	public int eliminaByIdProdottoAndIdOrdine(long id, long id_ordine);

	
	@Modifying
	@Transactional
	@Query("UPDATE RigaDOrdine r SET r.eliminato = true WHERE r.prodotto.id = :idProdotto")
	public int eliminaAllRigheByIdProdotto(long idProdotto);
	
	public Optional<RigaDOrdine> findRigaDOrdineByOrdine_idAndProdotto_id(long idOrdine, long idProdotto);
	
	
}
