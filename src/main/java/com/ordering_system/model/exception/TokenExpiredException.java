package com.ordering_system.model.exception;

import java.io.Serial;

public class TokenExpiredException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -8057466800167953593L;
	
    private String message;


    public TokenExpiredException() {

    }


    /**
     * @param message
     */
    public TokenExpiredException(String message) {
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
