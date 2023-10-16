package com.elis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Marca;

import jakarta.transaction.Transactional;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
	


	public Optional<Marca> findMarcaByNome(String nome);
	
	@Modifying
	@Transactional
	@Query("UPDATE Marca m SET m.eliminato = true WHERE m.id = :id")
	public int eliminaById(long id);
}
