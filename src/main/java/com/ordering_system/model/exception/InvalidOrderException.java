package com.ordering_system.model.exception;

import java.io.Serial;

public class InvalidOrderException extends  RuntimeException{
    @Serial
    private static final long serialVersionUID = -1195648288373131652L;

    private String message;


    public InvalidOrderException() {

    }


    /**
     * @param message
     */
    public InvalidOrderException(String message) {
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
