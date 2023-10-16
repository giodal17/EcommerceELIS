package com.elis.facade;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elis.dto.request.AcquistaProdottoRequest;
import com.elis.dto.request.IndirizzoRequest;
import com.elis.dto.request.LoginRequest;
import com.elis.dto.request.RegistrazioneRequest;
import com.elis.dto.request.RigaDOrdineRequest;
import com.elis.dto.request.UpdateIndirizzoRequest;
import com.elis.dto.request.modificaPswRequest;
import com.elis.dto.response.IndirizzoDTO;
import com.elis.dto.response.OrdineDTO;
import com.elis.dto.response.ProdottoDTO;
import com.elis.dto.response.RigaDOrdineDTO;
import com.elis.dto.response.UtenteDTO;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.mapper.CategoriaMapper;
import com.elis.mapper.IndirizzoMapper;
import com.elis.mapper.MarcaMapper;
import com.elis.mapper.OrdineMapper;
import com.elis.mapper.ProdottoMapper;
import com.elis.mapper.RigaDOrdineMapper;
import com.elis.mapper.SottocategoriaMapper;
import com.elis.mapper.UtenteMapper;
import com.elis.model.Indirizzo;
import com.elis.model.Ordine;
import com.elis.model.RigaDOrdine;
import com.elis.model.Ruolo;
import com.elis.model.Utente;
import com.elis.security.GestoreToken;
import com.elis.service.impl.CategoriaServiceImpl;
import com.elis.service.impl.IndirizzoServiceImpl;
import com.elis.service.impl.MarcaServiceImpl;
import com.elis.service.impl.OrdineServiceImpl;
import com.elis.service.impl.ProdottoServiceImpl;
import com.elis.service.impl.RigaDOrdineServiceImpl;
import com.elis.service.impl.SottocategoriaServiceImpl;
import com.elis.service.impl.UtenteServiceImpl;

@Service
public class UtenteUserFacade {
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
	
	@Autowired
	GestoreToken tokenP;
	//-------------------------------UTENTE--------------------------
	public UtenteDTO aggiungiUser(RegistrazioneRequest request) {
		request.setRuolo(Ruolo.USER);
		Utente u = utenteService.registrati(request);
		if(u==null) throw new OggettoNonTrovatoException(request, "Utente non trovato!");
		return utenteMapper.toDTO(u);
	}
	
	public String login(LoginRequest request){
		Utente u=utenteService.login(request);
		return tokenP.generateToken(u);
	}
	
	public UtenteDTO modificaPassword(modificaPswRequest request) {
		Utente u =  utenteService.modificaPassword(request);
		if(u==null) throw new OggettoNonTrovatoException(request, "Utente non trovato!");
		return utenteMapper.toDTO(u);
	}
	
	public String eliminaUtente(long id, HttpServletRequest request) {
		boolean flag = utenteService.eliminaUtente(id, request);
		if(flag) return "Utente eliminato";
		throw new DatiNonValidiException("Utente NON eliminato");
	}
	
	
	//----------------------------------INDIRIZZO--------------------------------
	public IndirizzoDTO aggiungiIndirizzo(IndirizzoRequest request) {
		Indirizzo indirizzo = indirizzoService.addIndirizzo(request);
		if(indirizzo==null) throw new OggettoNonTrovatoException(request, "Indirizzo non trovato");
		return indirizzoMapper.toDTO(indirizzo);
	}
	
	public IndirizzoDTO updateIndirizzo(UpdateIndirizzoRequest request) {
		Indirizzo i = indirizzoService.updateIndirizzo(request);
		if(i==null) throw new OggettoNonTrovatoException(request, "Indirizzo NON aggiornato");
		return indirizzoMapper.toDTO(i);
	}
	
	public String removeIndirizzoById(long id) {
		return indirizzoService.removeIndirizzoById(id);
	}
	
	public IndirizzoDTO getById(long id) {
		Indirizzo i = indirizzoService.getById(id);
		if(i==null)throw new OggettoNonTrovatoException(id, "Indirizzo NON trovato");
		return indirizzoMapper.toDTO(i);
	}
	
	public List<IndirizzoDTO> getAllIndirizzi(String email){
		List<Indirizzo> indirizzi = indirizzoService.getAllIndirizzi(email);
		return indirizzoMapper.toDTOList(indirizzi);
	}
	//------------------------------------ORDINE/RIGADORDINE----------------------------------
	public OrdineDTO acquistaProdotto(AcquistaProdottoRequest request) {
		Ordine o = ordineService.acquistaProdotto(request);
		if(o==null)throw new OggettoNonTrovatoException(request, "Ordine NON effettuato");
		return ordineMapper.toDTO(o);
	}
	
	public OrdineDTO confermaAcquisto(long idOrdine) {
		Ordine o = ordineService.confermaAcquisto(idOrdine);
		if(o==null) throw new DatiNonValidiException(o, "Ordine non confermato");
		return ordineMapper.toDTO(o);
	}
	
	public OrdineDTO getCarrello(String email) {
		 Ordine o = ordineService.getCarrelloPerVisualizzazzione(email);
		 if(o==null) throw new OggettoNonTrovatoException(email, "Carrello vuoto");
		 return ordineMapper.toDTO(o);
	 }
	
	public List<ProdottoDTO> findAll(){
		return prodottoMapper.toDTOList(prodottoService.findAll());
	}
	
	public List<OrdineDTO> getAllOrdiniConfermati(String email){
		List<Ordine> ordini = ordineService.getOrdiniConfermati(email);		
		if(ordini==null) throw new OggettoNonTrovatoException(email, "Nessun ordine confermato");
		return ordineMapper.toDTOList(ordini);
	}
	
	public RigaDOrdineDTO aggiungiQuantità(RigaDOrdineRequest request){
		RigaDOrdine riga = rigaDOrdineService.aggiungiQuantità(request);
		if(riga==null) throw new OggettoNonTrovatoException(request, "Nessuna riga d'ordine trovata");
		return rigaDOrdineMapper.toDTO(riga);
	}
	
	public RigaDOrdineDTO riduciQuantità(RigaDOrdineRequest request){
		RigaDOrdine riga = rigaDOrdineService.riduciQuantità(request);
		if(riga==null) throw new OggettoNonTrovatoException(request, "Nessuna riga d'ordine trovata");
		return rigaDOrdineMapper.toDTO(riga);
	}
	
	public boolean eliminaById(long id){
		return rigaDOrdineService.deleteById(id);
		
	}
	
	public boolean eliminaByIdProdotto(long idProdotto, long id_ordine){
		return rigaDOrdineService.eliminaRiga(idProdotto, id_ordine);
	}
}
