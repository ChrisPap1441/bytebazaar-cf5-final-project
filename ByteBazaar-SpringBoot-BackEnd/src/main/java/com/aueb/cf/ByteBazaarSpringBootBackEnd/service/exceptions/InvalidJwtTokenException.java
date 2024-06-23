package com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an invalid JWT token is encountered.
 *
 * @author Chris
 * @version 1.0
 */
public class InvalidJwtTokenException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidJwtTokenException with the specified detail message.
     *
     * @param s The detail message.
     */
    public InvalidJwtTokenException(String s) {
        super(s);
    }
}
