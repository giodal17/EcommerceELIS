package com.elis.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elis.dto.request.SottocategoriaRequest;
import com.elis.dto.response.CategoriaDTO;
import com.elis.dto.response.SottocategoriaDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.mapper.CategoriaMapper;
import com.elis.mapper.SottocategoriaMapper;
import com.elis.mapper.UtenteMapper;
import com.elis.model.Categoria;
import com.elis.model.Sottocategoria;
import com.elis.model.Utente;
import com.elis.service.impl.CategoriaServiceImpl;
import com.elis.service.impl.IndirizzoServiceImpl;
import com.elis.service.impl.MarcaServiceImpl;
import com.elis.service.impl.OrdineServiceImpl;
import com.elis.service.impl.ProdottoServiceImpl;
import com.elis.service.impl.RigaDOrdineServiceImpl;
import com.elis.service.impl.SottocategoriaServiceImpl;
import com.elis.service.impl.UtenteServiceImpl;

@Service
public class UtenteAdminFacade {
	
	@Autowired
	UtenteServiceImpl utenteService;
	
	@Autowired
	IndirizzoServiceImpl indirizzoService;
	
	@Autowired
	MarcaServiceImpl marcaService;
	
	@Autowired
	OrdineServiceImpl ordineService;
	
	@Autowired
	ProdottoServiceImpl prodottoService;
	
	@Autowired
	RigaDOrdineServiceImpl rigaDOrdineService;
	
	@Autowired
	SottocategoriaServiceImpl sottocategoriaService;
	
	@Autowired
	CategoriaServiceImpl categoriaService;
	
	@Autowired
	UtenteMapper utenteMapper;
	
	@Autowired
	CategoriaMapper categoriaMapper;
	
	@Autowired
	SottocategoriaMapper sottocategoriaMapper;

	public CategoriaDTO addCategoria(String nome) {
		Categoria c = categoriaService.addCategoria(nome);
		if(c==null) throw new DatiNonValidiException(nome, "Categoria NON Aggiunta");
		return categoriaMapper.toDTO(c);
	}
	
	public SottocategoriaDTO addSottocategoria(SottocategoriaRequest request) {
		Sottocategoria s = sottocategoriaService.addSottocategoria(request);
		if(s==null) throw new DatiNonValidiException(request, "Categoria NON Aggiunta");
		return sottocategoriaMapper.toDTO(s);
	}
	
	public List<UtenteDTO> getAllUtenti(){
		List<Utente> utenti = utenteService.getAllUtenti();
		if (utenti==null) throw new OggettoNonTrovatoException(0, "Utenti non presenti");
		return utenteMapper.toDTOList(utenti);
	}
}
