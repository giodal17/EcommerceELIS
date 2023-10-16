package com.elis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elis.dto.request.IndirizzoRequest;
import com.elis.dto.request.UpdateIndirizzoRequest;
import com.elis.dto.response.IndirizzoDTO;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Indirizzo;
import com.elis.model.Utente;
import com.elis.repository.IndirizzoRepository;
import com.elis.repository.UtenteRepository;
import com.elis.service.IndirizzoService;

@Service
public class IndirizzoServiceImpl implements IndirizzoService {

	private static IndirizzoServiceImpl istance = new IndirizzoServiceImpl();

	private IndirizzoServiceImpl() {
	}

	public static IndirizzoServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	IndirizzoRepository repo;

	@Autowired
	UtenteRepository utenteRepo;

	@Override
	public Indirizzo addIndirizzo(IndirizzoRequest request) {

		Indirizzo i = ottieniIndirizzo(request);
		return repo.save(i);

	}

	@Override
	public Indirizzo updateIndirizzo(UpdateIndirizzoRequest indirizzo) {
		Utente u = utenteRepo.findById(indirizzo.getIdUtente()).orElseThrow(() -> new OggettoNonTrovatoException(indirizzo.getIdUtente(), "Utente non trovato"));
		Indirizzo iVecchio = repo.findById(indirizzo.getIdIndirizzo()).orElseThrow(() -> new OggettoNonTrovatoException(indirizzo.getIdIndirizzo(), "Indirizzo non trovato"));
		Indirizzo iNuovo = new Indirizzo(indirizzo.getIdIndirizzo(), indirizzo.getIndirizzo1(), indirizzo.getProvincia(), indirizzo.getCap(), indirizzo.getNumeroTelefono(), indirizzo.getComune(), indirizzo.getNome(), u);
		if(iVecchio.isEliminato())return null;
		return repo.save(iNuovo);
	}

	@Override
	public String removeIndirizzoById(long id) {
		
		if(repo.eliminaById(id)>0) return "Indirizzo cancellato";
			return "Indirizzo NON csncellato!";
		
	}

	@Override
	public Indirizzo getById(long id) {
		
	 Indirizzo i = repo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "Indirizzo non trovato"));
	 if(i.isEliminato()) return null;
	 return i;
		
		
	}

	public Indirizzo ottieniIndirizzo(IndirizzoRequest request) {
		Utente u = utenteRepo.findById(request.getIdUtente())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Id utente non valido"));
		if (request.getCap().isBlank() || request.getIndirizzo1().isBlank() || request.getProvincia().isBlank()
				|| request.getNumeroTelefono().isBlank() || request.getComune().isBlank()
				|| request.getNome().isBlank())
			throw new DatiNonValidiException(request, "I campi non possono essere vuoti");
		return new Indirizzo(request.getIndirizzo1(), request.getProvincia(), request.getCap(),
				request.getNumeroTelefono(), request.getComune(), request.getNome(), u);

	}

	@Override
	public List<Indirizzo> getAllIndirizzi(String email) {
		Utente u = utenteRepo.findUtenteByEmail(email).orElseThrow(() -> new OggettoNonTrovatoException(email, "Utente non trovato"));
		return repo.findAllIndirizzoByUtente(u)
				.orElseThrow(() -> new DatiNonValidiException(email, "email non valida")).stream().filter(i -> i.isEliminato()== false).collect(Collectors.toList());
	}
}
