package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidPriceException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -116540936797740900L;
	
	private String message;
	
	public InvalidPriceException() {
		
	}

	/**
	 * @param message
	 */
	public InvalidPriceException(String message) {
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
