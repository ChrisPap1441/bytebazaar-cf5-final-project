package com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions;

import java.io.Serial;

/**
 * Exception thrown when a user is not found.
 *
 * @author Chris
 * @version 1.0
 */
public class UserNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param s The detail message.
     */
    public UserNotFoundException(String s) {
        super(s);
    }

    /**
     * Constructs a new UserNotFoundException with the specified user ID.
     *
     * @param id The ID of the user that was not found.
     */
    public UserNotFoundException(Long id) {
        super("User with id: " + id + " does not exits!");
    }
}
