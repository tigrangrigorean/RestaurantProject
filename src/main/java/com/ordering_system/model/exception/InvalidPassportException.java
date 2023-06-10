package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidPassportException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5826731362834176718L;

    private String message;


    public InvalidPassportException() {

    }


    /**
     * @param message
     */
    public InvalidPassportException(String message) {
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
