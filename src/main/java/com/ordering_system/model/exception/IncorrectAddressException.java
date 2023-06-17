package com.ordering_system.model.exception;

import java.io.Serial;

public class IncorrectAddressException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -1299276012710882072L;

    private String message;


    public IncorrectAddressException() {

    }


    /**
     * @param message
     */
    public IncorrectAddressException(String message) {
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
