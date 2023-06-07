package com.ordering_system.model.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = -876768575912466951L;
	
	private String message;
	
	
	public EntityNotFoundException() {
		
	}


	/**
	 * @param message
	 */
	public EntityNotFoundException(String message) {
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
