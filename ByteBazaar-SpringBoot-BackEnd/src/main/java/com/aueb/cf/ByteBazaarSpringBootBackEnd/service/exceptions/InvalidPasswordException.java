package com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an invalid password is encountered.
 *
 * @author Chris
 * @version 1.0
 */
public class InvalidPasswordException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidPasswordException with the specified detail message.
     *
     * @param s The detail message.
     */
    public InvalidPasswordException(String s) {
        super(s);
    }
}
