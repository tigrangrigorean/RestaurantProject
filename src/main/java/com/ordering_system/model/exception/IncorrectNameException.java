package com.ordering_system.model.exception;

import java.io.Serial;

public class IncorrectNameException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7023383604739705674L;

    private String message;


    public IncorrectNameException() {

    }


    /**
     * @param message
     */
    public IncorrectNameException(String message) {
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
