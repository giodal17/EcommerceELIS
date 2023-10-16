package com.elis.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;

import com.elis.model.Indirizzo;
import com.elis.model.Ruolo;
import com.elis.model.Utente;

public class SingletonUtente {
	
	private static SingletonUtente istance = new SingletonUtente();
	
	private SingletonUtente() {
		List<Indirizzo> indirizzi = new ArrayList<Indirizzo>();
		Utente u = new Utente("Giovanni", "D'Alessandro", "gio@gmail.com", "123456", Ruolo.ADMIN);
		Indirizzo indirizzo = new Indirizzo("Via Semeria", "BA", "70014", "3913725300", "Conversano", "Abitazione", u);
		indirizzi.add(indirizzo);
		u.setIndirizzi(indirizzi);
		utenti.add(u);
	}
	
	public static SingletonUtente getInstance() {
		return istance;
	}
	
	private List<Utente> utenti = new ArrayList<Utente>();
	
	public List<Utente> getUtenti() {
		return utenti;
	}

	
	//registrazione
	public Utente aggiungiUtente(Utente utente, String passwordConferma) {
		if(utente!=null && !utenti.contains(utente) && utente.getPassword().equals(passwordConferma)) {
			utenti.add(utente);
			return utente;
		}
		return null;
	}
	
	public Utente findById(int id) {
		return utenti.stream().filter(s -> s.getId()==id).findFirst().orElse(null);
	}
	public Utente findByEmail(String email) {
		return utenti.stream().filter(s -> s.getEmail().equals(email)).findFirst().orElse(null);
	}
	
	//login
	public Utente findByEmailAndPassword(String email, String password) {
		return utenti.stream().filter(s -> s.getEmail().equals(email) && s.getPassword().equals(password)).findFirst().orElse(null);
	}
	
	public boolean modificaEmail(int id, String email) {	
		if(email!=null && !email.isBlank() && email.matches("^(.+)@(\\S+)$")) {
			utenti.forEach(System.out ::println);
			if(findById(id)!=null && !utenti.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email))) {
				Utente utente = findById(id);
				utente.setEmail(email);
				utenti.forEach(System.out ::println);
				return true;
			}
		}
		return false;
	}
	
	public boolean modificaPassword (int id, String password) {
		if(password!=null && !password.isBlank()) {
			if(findById(id)!=null) {
				Utente utente = findById(id);
				if(utente.getPassword().equalsIgnoreCase(password)) {
					return false;
				}
				utente.setPassword(password);
				return true;
			}
		}
		return false;
	}
	
	
	
}
