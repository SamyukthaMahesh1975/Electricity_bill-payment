package com.cg.exception;

public class NoSuchConnectionException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	public NoSuchConnectionException(String message) {
		super(message);
	}
     
	public NoSuchConnectionException() {
		
	}

}
