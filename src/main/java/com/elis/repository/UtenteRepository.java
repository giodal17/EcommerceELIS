package com.elis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Utente;

import jakarta.transaction.Transactional;

public interface UtenteRepository extends JpaRepository<Utente, Long>{

	public Optional<Utente> findUtenteByEmailAndPassword(String email, String password);
	
	@Transactional
	@Modifying
	@Query("UPDATE Utente u set u.password = :psw where u.email = :email AND u.eliminato = false")
	public int modificaPsw(String psw, String email);
	
	public Optional<Utente> findUtenteByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE Utente u set u.eliminato = true WHERE u.id = :id")
	public int eliminaById(long id);
}
