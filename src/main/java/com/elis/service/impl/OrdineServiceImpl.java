package com.elis.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.elis.dto.request.AcquistaProdottoRequest;
import com.elis.dto.request.ProdottoQuantitàRequest;
import com.elis.dto.request.RigaDOrdineRequest;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Ordine;
import com.elis.model.Prodotto;
import com.elis.model.RigaDOrdine;
import com.elis.model.Utente;
import com.elis.repository.OrdineRepository;
import com.elis.repository.UtenteRepository;
import com.elis.service.OrdineService;

@Service
public  class OrdineServiceImpl implements OrdineService{
	
private static OrdineServiceImpl istance = new OrdineServiceImpl();
	
	private OrdineServiceImpl() {}
	
	public static OrdineServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	OrdineRepository repo;
	
	@Autowired
	UtenteRepository utenteRepo;
	
	@Autowired
	UtenteServiceImpl utenteService;

	@Autowired
	ProdottoServiceImpl prodottoService;

	@Autowired
	RigaDOrdineServiceImpl rigaDOrdineService;


	@Override
	public Ordine addOrdine(long idCliente) {
		Utente cliente = utenteRepo.findById(idCliente).orElse(null);
		Ordine o = new Ordine(cliente);
		o=repo.save(o);
		return o;
	}

	@Override
	public ResponseEntity<String> removeById(long id) {
		Ordine o = repo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "Ordine non trovato"));
		if(o.getDataInvioOrdine()!=null) return ResponseEntity.badRequest().body("L'ordine è stato già confermato");
			if(repo.eliminaById(id)>0)
				return ResponseEntity.ok().body("Ordine eliminato");
			return ResponseEntity.badRequest().body("Ordine NON eliminato");
	}

	@Override
	public Ordine getById(long id) {
		
			Ordine o = repo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "L'ordine non esiste!"));
			if(o.isEliminato()) return null; // controllo se l'ordine a cui vuole accedere non sia stato eliminato
			if(o.getRighe()==null)return o;
			o.setRighe(o.getRighe().stream().filter(r -> r.isEliminato() == false).collect(Collectors.toList()));
			return o;
			
			
	}

	@Override
	public Ordine getCarrello(String email) {
		
			Ordine carrello = repo.getAllOrdiniPending(email).orElse(null);
			if(carrello == null) return carrello;
			
			if(carrello.isEliminato()) return null; // controllo se l'ordine a cui vuole accedere non sia stato eliminato
			
			if(carrello.getRighe() == null) return carrello;
			carrello.setRighe(carrello.getRighe().stream().filter(r -> r.isEliminato()== false).toList());;
			return carrello;
	}
	
	public Ordine getCarrelloPerVisualizzazzione(String email) {
		Ordine carrello = repo.getAllOrdiniPending(email).orElseThrow(() -> new OggettoNonTrovatoException(email, "L'ordine non esiste!"));
		if(carrello.isEliminato()) return null; // controllo se l'ordine a cui vuole accedere non sia stato eliminato

		carrello.setRighe(carrello.getRighe().stream().filter(r -> r.isEliminato()== false).toList());;
		return carrello;
		
	}


	@Override
	public Ordine confermaAcquisto(long idOrdine) {
		LocalDate dataAdesso = LocalDate.now();
		Ordine o = repo.findById(idOrdine).orElseThrow(() -> new OggettoNonTrovatoException(idOrdine, "Ordine non trovato!"));
				if(!repo.existsById(idOrdine)) throw new OggettoNonTrovatoException(idOrdine, "L'ordine non esiste!");
				if(o.isEliminato())return null;
				if(o.getDataInvioOrdine()!=null) throw new DatiNonValidiException(idOrdine, "Ordine già confermato");
//				List<RigaDOrdine> righe = o.getRighe().stream().filter(r -> !r.isEliminato() || !r.getProdotto().isEliminato() ).toList();
//				o.setRighe(righe);
//				repo.save(o);
				repo.confermaAcquisto(idOrdine, dataAdesso);
				return repo.findById(idOrdine).orElse(null); 
	}

	@Override
	public List<Ordine> getOrdiniConfermati(String email) {
		
		utenteRepo.findUtenteByEmail(email).orElseThrow(() -> new OggettoNonTrovatoException(email, "Utente non trovato"));
		List<Ordine> ordiniConfermati = repo.getAllOrdiniConfermati(email).orElseThrow(() -> new OggettoNonTrovatoException(email, "Ordini confermati non trovati"));
		for(int i=0 ; i< ordiniConfermati.size();i++) {
			ordiniConfermati.get(i).setRighe(ordiniConfermati.get(i).getRighe().stream().filter(r -> r.isEliminato()== false).toList());
		}
		return ordiniConfermati.stream().filter(o -> o.isEliminato()== false).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<String> updatePrezzoTotale(long id, double prezzo) {
		Ordine o = repo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "L'ordine non esiste!"));
		if(o.isEliminato()) return ResponseEntity.badRequest().build(); // controllo se l'ordine a cui vuole accedere non sia stato eliminato

		int r = repo.updatePrezzoTotale(id, prezzo);
		if(r==0) throw new DatiNonValidiException(id, "Id non valido");
		return ResponseEntity.ok().build();
	}
	
	public Ordine acquistaProdotto(@RequestBody AcquistaProdottoRequest request) {

		if(prodottoService.getById(request.getIdProdotto()).isEliminato()) throw new OggettoNonTrovatoException(request, "Prodotto non trovato");
		
		if(utenteService.getByEmail(request.getEmail()).isEliminato()) throw new OggettoNonTrovatoException(request, "utente non trovato");
		Ordine carrello=getCarrello(request.getEmail());
		if (carrello != null) { // se c'è l'ordine allora mi prendo le varie righe d'ordine che corrispondono ai
										// vari prodotti nel carrello
				
			Ordine ordineInPending = carrello; // mi prendo il carrello, quindi
																				// l'ordine con la data_invio_confermata
																				// a null
			
			if (ordineInPending.isEliminato()) throw new OggettoNonTrovatoException(request, "Ordine non trovato");

			List<RigaDOrdine> righe = ordineInPending.getRighe().stream().filter(r-> !r.isEliminato()).toList(); // tolgo le righe eliminate
			Prodotto p=righe.stream().filter(r -> r.getProdotto().getId() == request.getIdProdotto()).map(RigaDOrdine::getProdotto).findFirst().orElse(null);
			if (p!=null) { // se trovo una riga
																									// con lo stesso
																									// prodotto che
																									// voglio inserire
																									// io allora aumento
																									// la quantità di
																									// quel prodotto in
																									// quella riga
				if (p.getQuantità() >= request.getQuantità()
						&& request.getQuantità() > 0) { // controllo se la quantità del prodotto che voglio acquistare
														// sia maggiore della quantiàa a disposizione del prodotto e che
														// la quantità in input sia maggiore di 0
					RigaDOrdineRequest requestRiga = new RigaDOrdineRequest(request.getQuantità(),
							ordineInPending.getId(), request.getIdProdotto());
					rigaDOrdineService.aggiungiQuantità(requestRiga);
					ProdottoQuantitàRequest prodQuantReq = new ProdottoQuantitàRequest(request.getQuantità(),
							request.getIdProdotto());
					prodottoService.riduciQuantità(prodQuantReq); // scalcolo la quantità a disposizione del prodotto (
																	// TODO ANZI è DA SCALCOLARE QUANDO CONFERMO
																	// L'ORDINE
					double prezzoIvato = (p.getPrezzo() +(p.getPrezzo() * p.getIva().getValore() /100)) *request.getQuantità(); 	// aggiorno
					// il
					// prezzo
					updatePrezzoTotale(ordineInPending.getId(), prezzoIvato);
					return carrello;
				} else {
					return null; // else dell'if del controllo
															// quantità
				}
			} else { // nel caso l'ordine c'è ma non c'è nel carrello il prodotto
				RigaDOrdineRequest requestRiga = new RigaDOrdineRequest(request.getQuantità(), ordineInPending.getId(),
						request.getIdProdotto());
				RigaDOrdine r1= rigaDOrdineService.addRigaDOrdine(requestRiga);
				ProdottoQuantitàRequest prodQuantReq = new ProdottoQuantitàRequest(request.getQuantità(),
						request.getIdProdotto());
				prodottoService.riduciQuantità(prodQuantReq); // scalcolo la quantità a disposizione del prodotto ( TODO
																// ANZI è DA SCALCOLARE QUANDO CONFERMO L'ORDINE
				double prezzoIvato = (p.getPrezzo() +(p.getPrezzo() * p.getIva().getValore() /100)) *request.getQuantità(); 	// aggiorno
				// il
				// prezzo
				if(carrello.getRighe()==null)carrello.setRighe(new ArrayList<>());
				carrello.getRighe().add(r1);
				updatePrezzoTotale(ordineInPending.getId(), prezzoIvato); //
				return carrello;

			}
		} else { 
			Prodotto p=prodottoService.getById(request.getIdProdotto());
			if (p!=null&&p.getQuantità() >= request.getQuantità() && request.getQuantità() > 0) { 															// gestione se l'ordine non esiste
			Utente cliente = utenteService.getByEmail(request.getEmail());
			Ordine ordineNuovo = new Ordine(cliente);
			ordineNuovo = repo.save(ordineNuovo);
			RigaDOrdineRequest requestRiga = new RigaDOrdineRequest(request.getQuantità(), ordineNuovo.getId(), request.getIdProdotto());
			RigaDOrdine r= rigaDOrdineService.addRigaDOrdine(requestRiga);
			if(ordineNuovo.getRighe()==null)ordineNuovo.setRighe(new ArrayList<>());
			ordineNuovo.getRighe().add(r);
			ProdottoQuantitàRequest prodQuantReq = new ProdottoQuantitàRequest(request.getQuantità(), request.getIdProdotto());
			prodottoService.riduciQuantità(prodQuantReq);
			double prezzoIvato = (p.getPrezzo() +(p.getPrezzo() * p.getIva().getValore() /100)) *request.getQuantità(); 	// aggiorno																												// prezzo
			updatePrezzoTotale(ordineNuovo.getId(), prezzoIvato); //
			return ordineNuovo;
			} // else dell'if del controllo quantità
		}
		return null;
		

	}
	
	public double calcolaESettaPrezzoIvato(long idOrdine) {
		
		Ordine o = repo.findById(idOrdine).orElseThrow(() -> new OggettoNonTrovatoException(idOrdine, "Ordine non trovato"));
		List<RigaDOrdine> righe = o.getRighe();
		double prezzoIvato = 0;
		for(RigaDOrdine r : righe) {
			if(r.isEliminato() || r.getProdotto().isEliminato()) break;
			prezzoIvato+= (r.getProdotto().getPrezzo()+ (r.getProdotto().getPrezzo()*r.getProdotto().getIva().getValore())/100)*r.getQuantita();
		}
		if(repo.updatePrezzoTotale(idOrdine, prezzoIvato)>0)
		return prezzoIvato;
		throw new DatiNonValidiException("Errore!");
	}
	
	
}
