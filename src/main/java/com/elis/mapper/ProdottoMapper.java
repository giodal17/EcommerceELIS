package com.elis.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elis.dto.response.MarcaDTO;
import com.elis.dto.response.ProdottoDTO;
import com.elis.dto.response.SottocategoriaDTO;
import com.elis.model.Marca;
import com.elis.model.Prodotto;
@Component
public class ProdottoMapper {


	public ProdottoDTO toDTO(Prodotto p) {
		if(p==null)return null;
		ProdottoDTO prodotto = new ProdottoDTO();
		prodotto.setNome(p.getNome());
		prodotto.setId(p.getId());
		prodotto.setDescrizione(p.getDescrizione());
		prodotto.setPrezzo(p.getPrezzo());
		prodotto.setMarca(p.getMarca().getNome());
		prodotto.setQuantità(p.getQuantità());
		prodotto.setSottocategorie(p.getSottocategoria().stream().map(SottocategoriaDTO::new).toList());
		prodotto.setIdVenditore(p.getIdVenditore());
		prodotto.setIVA(p.getIva().toString());
		return prodotto;
	}
	
	public List<ProdottoDTO> toDTOList(List<Prodotto> p){
		if(p==null) return null;
		return p.stream().map(this::toDTO).toList();
		
	}
}
