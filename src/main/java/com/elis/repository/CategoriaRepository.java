package com.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.elis.model.Categoria;

import jakarta.transaction.Transactional;
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	@Query("SELECT c FROM Categoria c WHERE c.nome = :nome")
	public Categoria findByNome(String nome);

	@Modifying
	@Transactional
	@Query("UPDATE Categoria c SET c.eliminato = true WHERE c.id = :id")
	public int eliminaById(long id);
	
	
}
