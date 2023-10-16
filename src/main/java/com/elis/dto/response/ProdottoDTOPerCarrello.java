package com.elis.dto.response;


	import java.util.List;

	import com.elis.dto.request.SottocategoriaRequest;
	import com.elis.model.Prodotto;
	import com.elis.model.Sottocategoria;

	public class ProdottoDTOPerCarrello {
		
		private long id;
		private String nome;
		private String descrizione;
		private double prezzo;
		private String marca;
		
		private List<SottocategoriaDTO> sottocategorie;
		private long idVenditore;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getDescrizione() {
			return descrizione;
		}
		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}
		public double getPrezzo() {
			return prezzo;
		}
		public void setPrezzo(double prezzo) {
			this.prezzo = prezzo;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		
		
		public long getIdVenditore() {
			return idVenditore;
		}
		public void setIdVenditore(long idVenditore) {
			this.idVenditore = idVenditore;
		}
		public ProdottoDTOPerCarrello(Prodotto p) {
			
			this.id = p.getId();
			this.nome = p.getNome();
			this.descrizione = p.getDescrizione();
			this.prezzo = (p.getPrezzo()*p.getIva().getValore()/100)+p.getPrezzo();
			this.marca = p.getMarca().getNome();
			this.setSottocategorie(p.getSottocategoria().stream().map(SottocategoriaDTO::new).toList());
			this.idVenditore = p.getIdVenditore();
		}
		public List<SottocategoriaDTO> getSottocategorie() {
			return sottocategorie;
		}
		public void setSottocategorie(List<SottocategoriaDTO> sottocategorie) {
			this.sottocategorie = sottocategorie;
		}
		
		
	}

