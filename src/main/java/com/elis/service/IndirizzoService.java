package com.elis.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.elis.dto.request.IndirizzoRequest;
import com.elis.dto.request.UpdateIndirizzoRequest;
import com.elis.dto.response.IndirizzoDTO;
import com.elis.model.Indirizzo;

public interface IndirizzoService {
	public Indirizzo addIndirizzo(IndirizzoRequest request);
	public Indirizzo updateIndirizzo(UpdateIndirizzoRequest indirizzo);
	public String removeIndirizzoById(long id);
	public Indirizzo getById (long id);
	public List<Indirizzo> getAllIndirizzi(String email);
}
