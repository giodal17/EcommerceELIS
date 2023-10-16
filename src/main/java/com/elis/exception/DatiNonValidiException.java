package com.elis.exception;

public class DatiNonValidiException extends RuntimeException {
	private Object request;
	public Object getRequest() {
		return request;
	}
	public void setRequest(Object request) {
		this.request = request;
	}
	
	public DatiNonValidiException(Object request, String message) {
		super(message);
		this.request = request;
		
	}
	public DatiNonValidiException(String message) {
		super(message);
	}
	

}
