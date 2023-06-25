package com.ordering_system.model.exception;

import java.io.Serial;

public class IncorrectPasswordException extends RuntimeException {
   
	@Serial
	private static final long serialVersionUID = 3109115685083030871L;
	
	private String message;


    public IncorrectPasswordException() {

    }


    /**
     * @param message
     */
    public IncorrectPasswordException(String message) {
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