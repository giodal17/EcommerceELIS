package com.elis.controller;


import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elis.dto.request.AcquistaProdottoRequest;
import com.elis.dto.request.IndirizzoRequest;
import com.elis.dto.request.RigaDOrdineRequest;
import com.elis.dto.request.modificaPswRequest;
import com.elis.dto.response.IndirizzoDTO;
import com.elis.dto.response.OrdineDTO;
import com.elis.dto.response.ProdottoDTO;
import com.elis.dto.response.RigaDOrdineDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.facade.UtenteUserFacade;
import com.elis.mapper.OrdineMapper;
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
@RequestMapping("/UtenteUser/")
public class UtenteUserController {

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
	UtenteUserFacade facade;
	
	@Autowired
	OrdineMapper ordineMapper;

	

	@PostMapping("AggiungiIndirizzo")
	public ResponseEntity<IndirizzoDTO> AggiungiIndirizzo(@Valid @RequestBody IndirizzoRequest request) {
		return ResponseEntity.ok(facade.aggiungiIndirizzo(request));
	}

	@GetMapping("GetIndirizzi")
	public ResponseEntity<List<IndirizzoDTO>> GetIndirizzi(@RequestParam("email") String email) {
		return ResponseEntity.ok(facade.getAllIndirizzi(email));
	}
	
	@GetMapping("GetIndirizzoById")
	public ResponseEntity<IndirizzoDTO> GetIndirizzoById(@RequestParam("id") long id) {
		return ResponseEntity.ok(facade.getById(id));
	}
	
	@PostMapping("DeleteIndirizzo")
	public ResponseEntity<String> EliminaIndirizzoById(@RequestParam("id") long id) {
		return ResponseEntity.ok(facade.removeIndirizzoById(id));
	}

	@PostMapping("ModificaPassword")
	public ResponseEntity<UtenteDTO> modificaPassword(@Valid @RequestBody modificaPswRequest request) {
		return ResponseEntity.ok(facade.modificaPassword(request));
	}

	@PostMapping("AcquistaProdotto")
	public ResponseEntity<OrdineDTO> acquistaProdotto(@Valid @RequestBody AcquistaProdottoRequest request) {
		return ResponseEntity.ok(facade.acquistaProdotto(request));
	}

	@PostMapping("ConfermaAcquisto")
	public ResponseEntity<OrdineDTO> confermaAcquisto(@RequestParam long idOrdine) {
		return ResponseEntity.ok(facade.confermaAcquisto(idOrdine));
	}

	@GetMapping("GetProdottiBySottocategoria")
	public ResponseEntity<List<ProdottoDTO>> getProdottiBySottocategoria(@RequestParam("nome") String nome) {
		return ResponseEntity.ok().body(sottocategoriaService.getProdottiBySottocategoria(nome));
	}
	
	@GetMapping("GetCarrello")
	public ResponseEntity<OrdineDTO> getCarrello(@RequestParam String email) {
		return ResponseEntity.ok().body(facade.getCarrello(email));
	}
	
	@PostMapping("EliminaUtente")
	public ResponseEntity<String> eliminaUtente(@RequestParam("id") long id, HttpServletRequest request){
		return ResponseEntity.ok().body(facade.eliminaUtente(id, request));
	}
	
	@GetMapping("GetOrdiniConfermati")
	public ResponseEntity<List<OrdineDTO>> getAllOrdiniConfermati(@RequestParam String email){
		return ResponseEntity.ok().body(facade.getAllOrdiniConfermati(email));
	
	}
	
	@PostMapping("AggiungiQuantità")
	public ResponseEntity<RigaDOrdineDTO> aggiungiQuantità(@RequestBody RigaDOrdineRequest request){
		return ResponseEntity.ok(facade.aggiungiQuantità(request));
	}
	
	@PostMapping("RiduciQuantità")
	public ResponseEntity<RigaDOrdineDTO> riduciQuantità(@RequestBody RigaDOrdineRequest request){
		return ResponseEntity.ok(facade.riduciQuantità(request));
	}
	
	@PostMapping("EliminaById")
	public ResponseEntity<String> eliminaById(@RequestParam long id){
		if(!facade.eliminaById(id)) return ResponseEntity.ok("La riga non è stata eliminata");
		return ResponseEntity.badRequest().body("La riga è stata elminata con successo");
	}
	
	@PostMapping("EliminaByIdProdotto")
	public ResponseEntity<String> eliminaByIdProdotto(@RequestBody RigaDOrdineRequest request){
		if(!facade.eliminaByIdProdotto(request.getIdProdotto(), request.getIdOrdine())) return ResponseEntity.ok("La riga non è stata eliminata");
		return ResponseEntity.badRequest().body("La riga è stata elminata con successo");
	}
	
}
