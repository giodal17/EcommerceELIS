package com.elis.mapper;
import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.ProdottoDTOPerCarrello;
import com.elis.dto.response.RigaDOrdineDTO;
import com.elis.model.RigaDOrdine;
@Component
public class RigaDOrdineMapper {
	
	public RigaDOrdineDTO toDTO(RigaDOrdine r) {
		if (r == null) {
			return null;
		}
		RigaDOrdineDTO riga = new RigaDOrdineDTO();
		riga.setProdotto(new ProdottoDTOPerCarrello(r.getProdotto()));
		riga.setQuantità(r.getQuantita());
		return riga;
	}
	
	public List<RigaDOrdineDTO> toDTOList(List<RigaDOrdine> r){
		if (r==null) return null;
		return r.stream().map(this::toDTO).toList();
	}
}
