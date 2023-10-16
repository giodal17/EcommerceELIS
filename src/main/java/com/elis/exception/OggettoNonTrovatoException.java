package com.elis.exception;

public class OggettoNonTrovatoException extends RuntimeException{
	
	private Object request;

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public OggettoNonTrovatoException(Object request, String message) {
		super(message);
		this.request = request;
	}
	
	public OggettoNonTrovatoException(long id, String message) {
		super(message);
		this.request=id;
	}
	
	

}
