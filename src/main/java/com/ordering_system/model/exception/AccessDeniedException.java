package com.ordering_system.model.exception;

import java.io.Serial;

public class AccessDeniedException {


    @Serial
    private static final long serialVersionUID = 3136866046210952341L;

    private String message;


    public AccessDeniedException() {

    }


    /**
     * @param message
     */
    public AccessDeniedException(String message) {
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
