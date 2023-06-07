package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidEmailException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = 806078347492945539L;

    private String message;


    public InvalidEmailException() {

    }

    /**
     * @param message
     */
    public InvalidEmailException(String message) {
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
