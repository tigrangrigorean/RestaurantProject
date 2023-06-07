package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidPasswordException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -4400969537131105900L;

    private String message;


    public InvalidPasswordException() {

    }

    /**
     * @param message
     */
    public InvalidPasswordException(String message) {
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
