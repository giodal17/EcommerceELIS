package com.elis.model;

public enum IVA {
	QUATTRO(4), DIECI(10), VENTIDUE(22);

	private final double valore;

	private IVA(double valore) {
		this.valore = valore;
	}

	public double getValore() {
		return valore;
	}

}
