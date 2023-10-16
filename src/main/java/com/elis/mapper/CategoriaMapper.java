package com.elis.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.CategoriaDTO;
import com.elis.dto.response.SottocategoriaDTO;
import com.elis.model.Categoria;

@Component
public class CategoriaMapper {

	public CategoriaDTO toDTO(Categoria c) {
		if(c==null)return null;
		CategoriaDTO categoria = new CategoriaDTO();
		categoria.setId(c.getId());
		categoria.setNome(c.getNome());
		categoria.setSottocategorie(c.getSottocategorie().stream().map(SottocategoriaDTO :: new).toList());
		return categoria;
	}
	
	public List<CategoriaDTO> toDTOList(List<Categoria> c){
		if(c==null) return null;
		return c.stream().map(this::toDTO).toList();
	}
}
