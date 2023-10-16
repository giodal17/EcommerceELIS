package com.elis.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.IndirizzoDTO;
import com.elis.model.Indirizzo;

@Component
public class IndirizzoMapper {
	public IndirizzoDTO toDTO(Indirizzo i) {
		if(i==null)return null;
		IndirizzoDTO indirizzo = new IndirizzoDTO();
		indirizzo.setCap(i.getCap());
		indirizzo.setComune(i.getComune());
		indirizzo.setId(i.getId());
		indirizzo.setIndirizzo1(i.getIndirizzo1());
		indirizzo.setNome(i.getNome());
		indirizzo.setNumeroTelefono(i.getNumeroTelefono());
		indirizzo.setProvincia(i.getProvincia());
		return indirizzo;
	}
	
	public List<IndirizzoDTO> toDTOList(List<Indirizzo> i){
		if(i==null) return null;
		return i.stream().map(this::toDTO).toList();
	}
}
