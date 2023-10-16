package com.elis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Indirizzo;
import com.elis.model.Utente;

import jakarta.transaction.Transactional;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {
	
	public Optional<List<Indirizzo>> findAllIndirizzoByUtente(Utente utente);
	
	@Modifying
	@Transactional
	@Query("UPDATE Indirizzo i SET i.eliminato = true WHERE i.id = :id")
	public int eliminaById(long id);
}
