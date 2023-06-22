package com.ordering_system.model.exception;

import java.io.Serial;

public class NotValidCardException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = -1371942974355156409L;

    private String message;


    public NotValidCardException() {

    }


    /**
     * @param message
     */
    public NotValidCardException(String message) {
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
