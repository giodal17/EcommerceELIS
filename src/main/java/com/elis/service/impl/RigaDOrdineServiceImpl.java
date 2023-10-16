package com.elis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elis.database.SingletonProdotto;
import com.elis.dto.request.RigaDOrdineRequest;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Ordine;
import com.elis.model.Prodotto;
import com.elis.model.RigaDOrdine;
import com.elis.repository.OrdineRepository;
import com.elis.repository.ProdottoRepository;
import com.elis.repository.RigaDOrdineRepository;
import com.elis.service.RigaDOrdineService;

@Service
public class RigaDOrdineServiceImpl implements RigaDOrdineService{

	
private static OrdineServiceImpl ordineService;

private static RigaDOrdineServiceImpl istance = new RigaDOrdineServiceImpl(ordineService);
	
	
	
	private RigaDOrdineServiceImpl(OrdineServiceImpl ordineService2) {
		super();
		ordineService = ordineService2;
	}

	public static RigaDOrdineServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	RigaDOrdineRepository rigaRepo;
	
	@Autowired
	ProdottoRepository prodottoRepo;
	
	@Autowired
	OrdineRepository ordineRepo;
	
	
	

	@Override
	public RigaDOrdine addRigaDOrdine(RigaDOrdineRequest request) {
			if(request.getQuantità()<=0) throw new DatiNonValidiException(request, "Quantità deve essere superiore a 0!");
			Prodotto p = prodottoRepo.findById(request.getIdProdotto()).orElseThrow(() -> new OggettoNonTrovatoException(request, "Prodotto non trovato"));
			Ordine o = ordineRepo.findById(request.getIdOrdine()).orElseThrow(() -> new OggettoNonTrovatoException(request, "Ordine non trovato"));
			RigaDOrdine rigaTrovata = rigaRepo.findRigaDOrdineByOrdine_idAndProdotto_id(o.getId(), p.getId()).orElse(null);
			if(rigaTrovata!=null && (request.getQuantità()>0||request.getQuantità()>=p.getQuantità())) {
				int nRighe = rigaRepo.aggiungiQuantità(rigaTrovata.getId(), request.getQuantità());
				rigaTrovata.diminuisciQuantita(request.getQuantità());
				if(nRighe>=0) {
					ordineRepo.updatePrezzoTotale(request.getIdOrdine(), p.getPrezzo()*request.getQuantità());
					return rigaTrovata;
				}
				return null;
			}else {
				RigaDOrdine r=new RigaDOrdine(p, request.getQuantità(), o);
				RigaDOrdine rNuova = rigaRepo.save(r);
			if (rNuova == null) {
				throw new OggettoNonTrovatoException(request, "Prodotto non aggiunto");
			}
			return rNuova;
			}
		
	}

	@Override
	public RigaDOrdine getById(long id) {
		return rigaRepo.findById(id).orElse(null);
	}

	@Override
	public boolean deleteById(long id) {
			long id_ordine = rigaRepo.findById(id).orElseThrow(() -> new OggettoNonTrovatoException(id, "Ordine non trovato")).getOrdine().getId();
			if(rigaRepo.eliminaById(id)>0) ordineService.calcolaESettaPrezzoIvato(id_ordine);
			return false;
		
	}

	@Override
	public RigaDOrdine riduciQuantità(RigaDOrdineRequest request) {
		
			RigaDOrdine rigaTrovata = ottieniRigaDOrdine(request);
			if(rigaRepo.rimuoviQuantità(rigaTrovata.getId(), request.getQuantità())>0) {
				ordineService.calcolaESettaPrezzoIvato(request.getIdOrdine());
				return getById(rigaTrovata.getId());
			}
			
			throw new DatiNonValidiException("Quantità non ridotta");
			
	}

	@Override
	public RigaDOrdine aggiungiQuantità(RigaDOrdineRequest request) {
		
		RigaDOrdine rigaTrovata = ottieniRigaDOrdine(request);
		if(rigaTrovata == null) throw new OggettoNonTrovatoException(request, "Riga d'ordine non trovata");
		if(rigaRepo.aggiungiQuantità(rigaTrovata.getId(), request.getQuantità())>0) {
			ordineService.calcolaESettaPrezzoIvato(request.getIdOrdine());
			return getById(rigaTrovata.getId());
		}
		
		throw new DatiNonValidiException("Quantità non aggiunta");
		

	}
	
	public RigaDOrdine ottieniRigaDOrdine(RigaDOrdineRequest request) {
		
			Prodotto p = prodottoRepo.findById(request.getIdProdotto()).orElse(null);
			Ordine o = ordineRepo.findById(request.getIdProdotto()).orElse(null);
			RigaDOrdine r = new RigaDOrdine(p, request.getQuantità(), o);
			List<RigaDOrdine> righeDOrdini = rigaRepo.findAll();
			return righeDOrdini.stream().filter(r1 -> r1.equals(r)).findFirst().orElse(null);
		
	}
	
	public boolean eliminaRiga(long idProdotto, long id_ordine) {
		int i = rigaRepo.eliminaByIdProdottoAndIdOrdine(idProdotto, id_ordine);
		if(i>0) 
			ordineService.calcolaESettaPrezzoIvato(id_ordine);
		return false;
	}
}
