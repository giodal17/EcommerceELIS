package com.elis.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.MarcaDTO;
import com.elis.dto.response.ProdottoDTO;
import com.elis.model.Marca;
@Component
public class MarcaMapper {
	public MarcaDTO toDTO(Marca m) {
		if(m==null) return null;
		MarcaDTO marca = new MarcaDTO();
		marca.setId(m.getId());
		marca.setNome(m.getNome());
		marca.setProdotti(m.getProdotti().stream().map(ProdottoDTO ::new).toList());
		return marca;
	}
	
	public List<MarcaDTO> toDTOList(List<Marca> m){
		if(m==null) return null;
		return m.stream().map(this::toDTO).toList();
		
	}
}
