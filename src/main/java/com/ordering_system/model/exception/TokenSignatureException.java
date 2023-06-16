package com.ordering_system.model.exception;

import java.io.Serial;

public class TokenSignatureException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = -1374339545821544241L;
	
	
    private String message;


    public TokenSignatureException() {

    }


    /**
     * @param message
     */
    public TokenSignatureException(String message) {
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
