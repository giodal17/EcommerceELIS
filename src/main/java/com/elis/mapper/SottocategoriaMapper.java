package com.elis.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.RigaDOrdineDTO;
import com.elis.dto.response.SottocategoriaDTO;
import com.elis.model.RigaDOrdine;
import com.elis.model.Sottocategoria;
@Component
public class SottocategoriaMapper {
	

	public SottocategoriaDTO toDTO(Sottocategoria s) {
		if(s==null) return null;
		SottocategoriaDTO sottocategoria = new SottocategoriaDTO();
		sottocategoria.setNomeCategoria(s.getCategoria().getNome());
		sottocategoria.setNomeSottocategoria(s.getNome());
		return sottocategoria;
	}
	public List<SottocategoriaDTO> toDTOList(List<Sottocategoria> r){
		if (r==null) return null;
		return r.stream().map(this::toDTO).toList();
	}
}
