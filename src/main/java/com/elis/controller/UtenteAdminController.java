package com.elis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elis.dto.request.SottocategoriaRequest;
import com.elis.dto.response.CategoriaDTO;
import com.elis.dto.response.SottocategoriaDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.facade.UtenteAdminFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/UtenteAdmin")
public class UtenteAdminController {
	
	@Autowired
	UtenteAdminFacade facade;

	@PostMapping("addCategoria")
	public ResponseEntity<CategoriaDTO> addCategoria(@Valid @RequestParam("nome") String nomeCategoria){
		return ResponseEntity.ok().body(facade.addCategoria(nomeCategoria));
	}

	@PostMapping("addSottocategoria")
	public ResponseEntity<SottocategoriaDTO> addSottocategoria(@Valid @RequestBody SottocategoriaRequest request){
		return ResponseEntity.ok().body(facade.addSottocategoria(request));
	}
	
	
	@GetMapping("/GetUtenti")
	public ResponseEntity<List<UtenteDTO>> getUtenti(){
		return ResponseEntity.ok().body(facade.getAllUtenti());
	}

}
