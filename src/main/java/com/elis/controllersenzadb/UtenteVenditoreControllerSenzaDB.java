package com.elis.controllersenzadb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elis.model.Prodotto;

@RestController
@RequestMapping("/UtenteVenditore")
public class UtenteVenditoreControllerSenzaDB {
	
	public ResponseEntity<String> inserisciArticolo(@RequestBody Prodotto prodotto){
		
		
		
		return null;
		
	}

}
