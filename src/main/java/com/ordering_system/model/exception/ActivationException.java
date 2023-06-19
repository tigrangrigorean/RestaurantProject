package com.ordering_system.model.exception;

import java.io.Serial;

public class ActivationException extends RuntimeException{
	
	@Serial
	private static final long serialVersionUID = 3136866046210952341L;
	
	private String message;
	
	
	public ActivationException() {
		
	}


	/**
	 * @param message
	 */
	public ActivationException(String message) {
		super();
		this.message = message;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
