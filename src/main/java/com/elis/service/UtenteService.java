package com.elis.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import com.elis.dto.request.LoginRequest;
import com.elis.dto.request.RegistrazioneRequest;
import com.elis.dto.request.modificaPswRequest;
import com.elis.dto.response.UtenteDTO;
import com.elis.model.Utente;

public interface UtenteService {

	public Utente registrati(RegistrazioneRequest request) throws SQLIntegrityConstraintViolationException;
	public Utente login(LoginRequest request);
	public Utente modificaPassword(modificaPswRequest request);
	public Utente getById(long id);
	public Utente getByEmail(String email);
	public List<Utente> getAllUtenti();
	public boolean eliminaUtente(long id, HttpServletRequest request);
}
