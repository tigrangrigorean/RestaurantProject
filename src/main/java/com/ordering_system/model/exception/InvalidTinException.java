package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidTinException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6947459596063417641L;

    private String message;


    public InvalidTinException() {

    }


    /**
     * @param message
     */
    public InvalidTinException(String message) {
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
