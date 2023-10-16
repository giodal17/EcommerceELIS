package com.elis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elis.dto.request.AddProdottoRequest;
import com.elis.dto.request.EliminazioneProdottoRequest;
import com.elis.dto.request.LoginRequest;
import com.elis.dto.request.ProdottoQuantitàRequest;
import com.elis.dto.request.RegistrazioneRequest;
import com.elis.dto.response.ProdottoDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.facade.UtenteVenditoreFacade;
import com.elis.service.impl.CategoriaServiceImpl;
import com.elis.service.impl.IndirizzoServiceImpl;
import com.elis.service.impl.MarcaServiceImpl;
import com.elis.service.impl.OrdineServiceImpl;
import com.elis.service.impl.ProdottoServiceImpl;
import com.elis.service.impl.RigaDOrdineServiceImpl;
import com.elis.service.impl.SottocategoriaServiceImpl;
import com.elis.service.impl.UtenteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("UtenteVenditore")
public class UtenteVenditoreController {
	
	@Autowired
	UtenteServiceImpl utenteService;
	
	@Autowired
	IndirizzoServiceImpl indirizzoService;
	
	@Autowired
	MarcaServiceImpl marcaService;
	
	@Autowired
	OrdineServiceImpl ordineService;
	
	@Autowired
	ProdottoServiceImpl prodottoService;
	
	@Autowired
	RigaDOrdineServiceImpl rigaDOrdineService;
	
	@Autowired
	SottocategoriaServiceImpl sottocategoriaService;
	
	@Autowired
	CategoriaServiceImpl categoriaService;
	
	@Autowired
	UtenteVenditoreFacade facade;
	
	@PostMapping("AggiungiProdotto")
	public ResponseEntity<ProdottoDTO> inserisciArticolo(@Valid @RequestBody AddProdottoRequest request){
		return ResponseEntity.ok(facade.inserisciArticolo(request));
	}
	
	
	@PostMapping("EliminaProdotto")
	public ResponseEntity<String> eliminaProdotto(@Valid @RequestBody EliminazioneProdottoRequest request){
		return ResponseEntity.ok(prodottoService.removeById(request));
	}
	
	@PostMapping("/AddQuantità")
	public ResponseEntity<ProdottoDTO> addQuantità(@Valid @RequestBody ProdottoQuantitàRequest request){
		return ResponseEntity.ok(facade.aggiungiQuantità(request));
	}
	
	@PostMapping("/RiduciQuantità")
	public ResponseEntity<ProdottoDTO> riduciQuantità(@Valid @RequestBody ProdottoQuantitàRequest request){
		return ResponseEntity.ok(facade.riduciQuantità(request));
	}
	
	@PostMapping("/RendiVisibile")
	public ResponseEntity<ProdottoDTO> rendiVisibile(@Valid @RequestBody EliminazioneProdottoRequest request){
		return ResponseEntity.ok(facade.rendiVisibile(request));
	}
	
	@PostMapping("getAllProdottiByVenditore")
	public ResponseEntity<List<ProdottoDTO>> getAllProdotti(long id){
		return ResponseEntity.ok(facade.getAllProdottiByVenditore(id));
	}
	
	
}
