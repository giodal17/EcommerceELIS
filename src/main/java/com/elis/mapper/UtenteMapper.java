package com.elis.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.IndirizzoDTO;
import com.elis.dto.response.OrdineDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.model.Utente;

@Component
public class UtenteMapper {
	
	public UtenteDTO toDTO(Utente u) {
		if(u==null) return null;
		UtenteDTO utente = new UtenteDTO();
		utente.setEmail(u.getEmail());
		utente.setNome(u.getNome());
		utente.setCognome(u.getCognome());
		utente.setId(u.getId());
		utente.setPassword(u.getPassword());
		utente.setIndirizzi(u.getIndirizzi().stream().map(IndirizzoDTO :: new).toList());
		utente.setRuolo(u.getRuolo().getRuolo());
		utente.setOrdini(u.getOrdini().stream().map(OrdineDTO :: new).toList());
		return utente;
	}
	
	public List<UtenteDTO> toDTOList(List<Utente> u){
		if(u==null) return null;
		return u.stream().map(this::toDTO).toList();
	}
}
