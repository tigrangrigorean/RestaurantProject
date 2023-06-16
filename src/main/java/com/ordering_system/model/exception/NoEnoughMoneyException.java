package com.ordering_system.model.exception;

import java.io.Serial;

public class NoEnoughMoneyException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = -1371942974355156409L;

    private String message;


    public NoEnoughMoneyException() {

    }


    /**
     * @param message
     */
    public NoEnoughMoneyException(String message) {
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
