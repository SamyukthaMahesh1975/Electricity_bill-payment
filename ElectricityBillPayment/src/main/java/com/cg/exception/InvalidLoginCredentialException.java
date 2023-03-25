package com.cg.exception;


public class InvalidLoginCredentialException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public  InvalidLoginCredentialException(String message) {
		super(message);
		
	}
     
	public  InvalidLoginCredentialException() {
		
	}

}