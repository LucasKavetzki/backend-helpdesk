package com.lucas.helpdesk.service.exptions;

public class ObjectnotFouldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectnotFouldException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectnotFouldException(String message) {
		super(message);
	}  

	

	
	
}
