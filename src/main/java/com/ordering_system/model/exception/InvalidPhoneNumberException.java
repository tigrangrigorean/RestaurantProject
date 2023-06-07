package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidPhoneNumberException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2105713896324200405L;

    private String message;


    public InvalidPhoneNumberException() {

    }


    /**
     * @param message
     */
    public InvalidPhoneNumberException(String message) {
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
