package com.elis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elis.dto.request.AddProdottoRequest;
import com.elis.dto.request.EliminazioneProdottoRequest;
import com.elis.dto.request.ProdottoQuantitàRequest;
import com.elis.dto.request.UpdateProdottoRequest;
import com.elis.exception.DatiNonValidiException;
import com.elis.exception.OggettoNonTrovatoException;
import com.elis.model.Marca;
import com.elis.model.Prodotto;
import com.elis.model.Sottocategoria;
import com.elis.repository.MarcaRepository;
import com.elis.repository.ProdottoRepository;
import com.elis.repository.RigaDOrdineRepository;
import com.elis.repository.SottocategoriaRepository;
import com.elis.repository.UtenteRepository;
import com.elis.service.ProdottoService;

@Service
public class ProdottoServiceImpl implements ProdottoService {

	private static ProdottoServiceImpl istance = new ProdottoServiceImpl();

	private ProdottoServiceImpl() {
	}

	public static ProdottoServiceImpl getIstance() {
		return istance;
	}

	@Autowired
	UtenteRepository utenteRepo;

	@Autowired
	ProdottoRepository repo;

	@Autowired
	MarcaRepository marcaRepo;

	@Autowired
	SottocategoriaRepository sCatRepo;
	
	@Autowired
	RigaDOrdineRepository rigaRepo;
	

	@Override
	public Prodotto addProdotto(AddProdottoRequest request) {

		Marca m = marcaRepo.findMarcaByNome(request.getMarca()).orElse(null);

		if (m == null) {
			m = new Marca(request.getMarca());
			marcaRepo.save(m);
		}
		if (m.isEliminato())
			throw new OggettoNonTrovatoException(request, "Marca non trovata o eliminata!");
		List<Sottocategoria> sLista = new ArrayList<Sottocategoria>();
		Sottocategoria sNew = sCatRepo.findByNome(request.getNomeSottocategoria());
		sLista.add(sNew);
		if (sNew.isEliminato())
			throw new OggettoNonTrovatoException(request, "Sottocategoria non trovata o eliminata!");
		Prodotto p = new Prodotto(request.getNome(), request.getDescrizione(), request.getPrezzo(), m,
				request.getQuantità(), sLista, request.getIdVenditore(), request.getIva());
		return repo.save(p);
	}

	@Override
	public Prodotto aggiungiQuantità(ProdottoQuantitàRequest request) {

		Prodotto p = repo.findById(request.getIdProdotto())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Prodotto non trovato"));
		if (p.isEliminato())
			throw new OggettoNonTrovatoException(request, "Il prodotto non esiste o è stato eliminato!");
		if (request.getQuantità() <= 0)
			throw new DatiNonValidiException(request, "La quantità deve essere maggiore di 0");
		int i = repo.aggiungiQuantità(request.getQuantità(), request.getIdProdotto());
		if(i>0) return repo.findById(request.getIdProdotto()).orElse(null);
		return null;

	}

	@Override
	public Prodotto riduciQuantità(ProdottoQuantitàRequest request) {

		Prodotto p = repo.findById(request.getIdProdotto())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Prodotto non trovato"));
		if (p.isEliminato())
			throw new OggettoNonTrovatoException(request, "Il prodotto non esiste o è stato eliminato!");
		if (request.getQuantità() <= 0)
			throw new DatiNonValidiException(request, "Quantità insufficiente");
		EliminazioneProdottoRequest req = new EliminazioneProdottoRequest(request.getIdProdotto());
		if(p.getQuantità()== request.getQuantità()) removeById(req);
		if(p.getQuantità()<request.getQuantità()) throw new DatiNonValidiException(request, "La quantità deve essere maggiore di 0");
		int i = repo.riduciQuantità(request.getQuantità(), request.getIdProdotto());
		if(i>0) return repo.findById(request.getIdProdotto()).orElse(null);
		return null;

	}

	@Override
	public String removeById(EliminazioneProdottoRequest request) {
		Prodotto p = repo.findById(request.getIdProdotto())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Prodotto non trovato"));
		if (p.isEliminato())
			throw new OggettoNonTrovatoException(request, "Prodotto non trovato");
		if (p.getIdVenditore() == request.getIdVenditore()) {
			int i = repo.eliminaById(p.getId());
			if (i > 0) {
				rigaRepo.eliminaAllRigheByIdProdotto(request.getIdProdotto());
				return "Prodotto eliminato!";
				}
			return "Prodotto NON eliminato";
		}
		throw new OggettoNonTrovatoException(request, "Prodotto non trovato");
	}

	@Override
	public Prodotto updateProdotto(UpdateProdottoRequest request) {

		Prodotto p1 = repo.findById(request.getId())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Prodotto non trovato"));

		Marca m = marcaRepo.findMarcaByNome(request.getMarca()).orElse(null);

		if (m == null) {
			m = new Marca(request.getMarca());
			marcaRepo.save(m);
		}
		if (m.isEliminato())
			throw new OggettoNonTrovatoException(request, "Marca non trovata");
		List<Sottocategoria> sLista = new ArrayList<Sottocategoria>();
		Sottocategoria sNew = sCatRepo.findByNome(request.getNomeSottocategoria());
		if (sNew.isEliminato())
			throw new OggettoNonTrovatoException(request, "Sottocategoria non trovata o eliminata!");
		sLista.add(sNew);
		Prodotto p = new Prodotto(request.getNome(), request.getDescrizione(), request.getPrezzo(), m,
				request.getQuantità(), sLista, request.getIdVenditore(), p1.getIva());
		return repo.save(p);
	}

	@Override
	public Prodotto getById(long id) {

		Prodotto p = repo.findById(id).orElse(null);
		if (p == null || p.isEliminato())
			throw new OggettoNonTrovatoException(id, "Prodotto non trovato");
		return p;
	}

	@Override
	public List<Prodotto> getAllProdottiByVenditore(long idVenditore) {
		return repo.findAllProdottoByidVenditore(idVenditore)
				.orElseThrow(() -> new OggettoNonTrovatoException(idVenditore, "Prodotto non trovato")).stream()
				.filter(p -> p.isEliminato() == false).toList();
	}

	@Override
	public Prodotto rendiVisibile(EliminazioneProdottoRequest request) {
		Prodotto p = repo.findById(request.getIdProdotto())
				.orElseThrow(() -> new OggettoNonTrovatoException(request, "Prodotto non trovato"));
		if (!p.isEliminato())
			throw new DatiNonValidiException(request, "Prodotto già visibile");
		if (p.getIdVenditore() == request.getIdVenditore()) {
			int i = repo.rendiVisibileById(request.getIdProdotto());
			if (i > 0)
				return repo.findById(request.getIdProdotto()).get();
			return null;
		}
		throw new OggettoNonTrovatoException(request, "Prodotto non trovato");
	}

	@Override
	public List<Prodotto> findAll() {
		List<Prodotto> prodotti = repo.findAll();
		if(prodotti.isEmpty()) throw new OggettoNonTrovatoException(0, "Prodotti non esistenti");
		return prodotti;
	}
}
