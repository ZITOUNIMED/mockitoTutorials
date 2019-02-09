package com.example.mockitoTutorials.exception;

public class UnablePurchaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnablePurchaseException(String message) {
		super(message);
	}
	
	public UnablePurchaseException() {
		super("Unable purchase order.");
	}
}
