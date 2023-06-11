package com.ordering_system.model.exception;

import java.io.Serial;

public class EntityAlreadyExsistsException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -3488764999991330054L;
	
	private String message;


    public EntityAlreadyExsistsException() {

    }


    /**
     * @param message
     */
    public EntityAlreadyExsistsException(String message) {
        this.message = message;
    }


    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }


}
