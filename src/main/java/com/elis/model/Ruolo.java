package com.elis.model;

public enum Ruolo {
	ADMIN("ROLE_ADMIN"),
	VENDITORE("ROLE_VENDITORE"),
	USER("ROLE_USER");
	
private String ruolo;
	
	private Ruolo(String s) {
		ruolo=s;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public String getRuoloTrimmed() {
		return ruolo.substring(5);
	}
}
