package com.ordering_system.model.exception;

import java.io.Serial;

public class InCorrectTextFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4577846781086559475L;

    private String message;


    public InCorrectTextFormatException() {

    }


    /**
     * @param message
     */
    public InCorrectTextFormatException(String message) {
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
