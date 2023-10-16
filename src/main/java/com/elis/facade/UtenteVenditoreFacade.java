package com.elis.facade;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.elis.dto.request.AddProdottoRequest;
import com.elis.dto.request.EliminazioneProdottoRequest;
import com.elis.dto.request.ProdottoQuantitàRequest;
import com.elis.dto.request.RegistrazioneRequest;
import com.elis.dto.response.ProdottoDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.mapper.CategoriaMapper;
import com.elis.mapper.IndirizzoMapper;
import com.elis.mapper.MarcaMapper;
import com.elis.mapper.OrdineMapper;
import com.elis.mapper.ProdottoMapper;
import com.elis.mapper.RigaDOrdineMapper;
import com.elis.mapper.SottocategoriaMapper;
import com.elis.mapper.UtenteMapper;
import com.elis.model.Prodotto;
import com.elis.model.Ruolo;
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
public class UtenteVenditoreFacade implements UserDetails{

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
	CategoriaMapper categoriaMapper;
	
	@Autowired
	IndirizzoMapper indirizzoMapper;
	
	@Autowired
	MarcaMapper marcaMapper;
	
	@Autowired
	OrdineMapper ordineMapper;
	
	@Autowired
	ProdottoMapper prodottoMapper;
	
	@Autowired
	RigaDOrdineMapper rigaDOrdineMapper;
	
	@Autowired
	SottocategoriaMapper sottocategoriaMapper;
	
	@Autowired
	UtenteMapper utenteMapper;
	
	public ProdottoDTO inserisciArticolo(AddProdottoRequest request) {
		Prodotto p = prodottoService.addProdotto(request);
		if (p == null) {
			return null;
		}return prodottoMapper.toDTO(p);
	}
	
	public UtenteDTO aggiungiVenditore(RegistrazioneRequest request) {
		request.setRuolo(Ruolo.VENDITORE);
		Utente u = utenteService.registrati(request);
		if(u== null) throw new OggettoNonTrovatoException(request, "Utente NON aggiunto");
		return utenteMapper.toDTO(u);
	}
	
	public List<ProdottoDTO> getAllProdottiByVenditore(long id) {
		List<Prodotto> prodotti = prodottoService.getAllProdottiByVenditore(id);
		if(prodotti==null) throw new OggettoNonTrovatoException(id, "Nessun prodotto trovato!");
		return prodottoMapper.toDTOList(prodotti);
	}
	
	public ProdottoDTO aggiungiQuantità(ProdottoQuantitàRequest request) {
		Prodotto p = prodottoService.aggiungiQuantità(request);
		if(p==null)throw new OggettoNonTrovatoException(request, "Quantità NON aggiunta!");
				return prodottoMapper.toDTO(p);
	}
	
	public ProdottoDTO riduciQuantità(ProdottoQuantitàRequest request) {
		Prodotto p = prodottoService.riduciQuantità(request);
		if(p==null)throw new OggettoNonTrovatoException(request, "Quantità NON ridotta!");
				return prodottoMapper.toDTO(p);
	}
	
	public ProdottoDTO rendiVisibile(EliminazioneProdottoRequest request) {
		Prodotto p = prodottoService.rendiVisibile(request);
		if(p==null)throw new OggettoNonTrovatoException(request, "Prodotto NON reso visibile!");
		return prodottoMapper.toDTO(p);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
