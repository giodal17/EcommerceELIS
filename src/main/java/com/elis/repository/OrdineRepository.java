package com.elis.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Ordine;

import jakarta.transaction.Transactional;
public interface OrdineRepository extends JpaRepository<Ordine,Long>{
	
	@Query("SELECT o FROM Ordine o WHERE o.cliente.email = :email AND o.dataInvioOrdine = null")
	public Optional<Ordine> getAllOrdiniPending(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Ordine o SET o.dataInvioOrdine = :data WHERE o.id = :id")
	public int confermaAcquisto(long id, LocalDate data);
	
	@Query("SELECT o FROM Ordine o WHERE o.cliente.email = :email AND o.dataInvioOrdine != null")
	public Optional<List<Ordine>> getAllOrdiniConfermati(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Ordine o SET o.prezzoTotaleIvato = o.prezzoTotaleIvato + :prezzo WHERE o.id = :id")
	public int updatePrezzoTotale(long id, double prezzo);
	
	@Modifying
	@Transactional
	@Query("UPDATE Ordine o SET o.eliminato = true WHERE o.id = :id")
	public int eliminaById(long id);
}
