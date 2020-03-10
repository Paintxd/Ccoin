package com.paint.ccoin.exception;

public class FoundsException extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String mesage = "Fundos unsuficientes para a operação";
	
	public FoundsException() {
		super(mesage);
	}
}
