package com.elis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elis.dto.request.LoginRequest;
import com.elis.dto.request.RegistrazioneRequest;
import com.elis.dto.response.ProdottoDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.facade.UtenteUserFacade;
import com.elis.facade.UtenteVenditoreFacade;
import com.elis.mapper.UtenteMapper;
import com.elis.model.Utente;
import com.elis.service.impl.UtenteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/All/")
public class AllController {
	
	@Autowired
	UtenteUserFacade userFacade;
	
	@Autowired
	UtenteVenditoreFacade vendFacade;
	
	@Autowired
	UtenteServiceImpl service;
	
	@Autowired
	UtenteMapper mapper;
	
	@PostMapping("Login")
	public ResponseEntity<UtenteDTO> Login(@Valid @RequestBody LoginRequest request)  {
		String s=userFacade.login(request);
		Utente u = service.getByEmail(request.getEmail());
		UtenteDTO uDTO = mapper.toDTO(u);
		return ResponseEntity.status(HttpStatus.OK).header("Authorization", s).body(uDTO);
	}
	
	@PostMapping("User/Registrazione")
	public ResponseEntity<UtenteDTO> AggiungiUser(@Valid @RequestBody RegistrazioneRequest request) {
		return ResponseEntity.ok(userFacade.aggiungiUser(request));
	}
	
	@PostMapping("Venditore/Registrazione")
	public ResponseEntity<UtenteDTO> AggiungiVenditore(@Valid @RequestBody RegistrazioneRequest request){
		return ResponseEntity.ok(vendFacade.aggiungiVenditore(request));
	}
	
	@GetMapping("FindAll")
	public ResponseEntity<List<ProdottoDTO>> findAll(){
		return ResponseEntity.ok(userFacade.findAll());
	}
}
