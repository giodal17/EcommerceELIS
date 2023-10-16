package com.elis.service.impl;

import java.util.List;

import com.elis.security.GestoreToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elis.dto.request.LoginRequest;
import com.elis.dto.request.RegistrazioneRequest;
import com.elis.dto.request.modificaPswRequest;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Utente;
import com.elis.repository.UtenteRepository;
import com.elis.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService {

	private static UtenteServiceImpl istance = new UtenteServiceImpl();

	private UtenteServiceImpl() {
	}

	public static UtenteServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	UtenteRepository repo;

	@Autowired
	GestoreToken gestoreToken;

	@Override
	public Utente registrati(RegistrazioneRequest request) {
		if (request.getPassword().equals(request.getPasswordConferma())) {
			Utente u = new Utente(request.getNome(), request.getCognome(), request.getEmail(), request.getPassword(),
					request.getRuolo());
			return repo.save(u);
		}
		throw new DatiNonValidiException(request, "Password non combaciano");
	}

	@Override
	public Utente login(LoginRequest request) {
		return repo.findUtenteByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Utente non trovato"));

	}

	@Override
	public Utente modificaPassword(modificaPswRequest request) {
		Utente u = repo.findUtenteByEmail(request.getEmail()).orElseThrow(() -> new OggettoNonTrovatoException(request, "Utente non trovato"));
		if(u.isEliminato()) throw new DatiNonValidiException(request, "L'utente è stato eliminato");
		if (!request.getPasswordVecchia().equals(repo.findUtenteByEmail(request.getEmail())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Utente non trovato")).getPassword()))
			throw new DatiNonValidiException(request, "Password attuale sbagliata!");
		if (request.getPasswordNuova().equals(request.getPasswordConferma())) {
			if (!request.getPasswordNuova().equalsIgnoreCase(request.getPasswordVecchia())) {
				int i = repo.modificaPsw(request.getPasswordNuova(), request.getEmail());
				if(i>0)return getByEmail(request.getEmail());
				throw new DatiNonValidiException(request, "Password NON modificata");
			} else {
				throw new DatiNonValidiException(request, "La password deve essere diversa da quella vecchia");
			}
		} else {
			throw new DatiNonValidiException(request, "Le password non combaciano");
		}
	}

	@Override
	public Utente getById(long id){
			Utente u = repo.findById(id).orElse(null);
			if(u.isEliminato()) throw new DatiNonValidiException(id, "L'utente è stato eliminato");
			return u;
	}

	@Override
	public Utente getByEmail(String email) {
		Utente u =  repo.findUtenteByEmail(email)
				.orElseThrow(() -> new OggettoNonTrovatoException(email, "Utente non trovato"));
		if(u.isEliminato()) throw new DatiNonValidiException(email, "L'utente è stato eliminato");
		return u;
	}

	@Override
	public List<Utente> getAllUtenti() {
		return repo.findAll().stream().filter(u -> !u.isEliminato()).toList();
	}

	@Override
	public boolean eliminaUtente(long id, HttpServletRequest request) {
		Utente utenteRequest = gestoreToken.prendiUtenteDaToken(request.getHeader("Authorization").substring(7));
		Utente u = repo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "Utente non trovato!"));
		return !u.isEliminato() && utenteRequest.equals(u) && repo.eliminaById(id) > 0;
	}

}
