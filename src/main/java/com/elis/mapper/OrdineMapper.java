package com.elis.mapper;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.OrdineDTO;
import com.elis.dto.response.RigaDOrdineDTO;
import com.elis.model.Ordine;
@Component
public class OrdineMapper {

	public OrdineDTO toDTO(Ordine o) {
		if(o==null)return null;
		OrdineDTO ordine = new OrdineDTO();
		ordine.setId(o.getId());
		ordine.setRighe(o.getRighe().stream().map(RigaDOrdineDTO::new).toList());
		ordine.setDataInvioOrdine(o.getDataInvioOrdine());
		ordine.setPrezzoTotaleIvato(o.getPrezzoTotaleIvato());
		return ordine;
	}
	public List<OrdineDTO> toDTOList(List<Ordine> a){
		if(a==null) return null;
		return a.stream().map(this::toDTO).toList();
		
	}
	
	
}

