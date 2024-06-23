package com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to register or use a username that already exists in the system.
 *
 * @author Chris
 * @version 1.0
 */
public class UserAlreadyExistsException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserAlreadyExistsException with the specified username.
     *
     * @param username The username that already exists.
     */
    public UserAlreadyExistsException(String username) {
        super("User with username: " + username + " already exists");
    }
}
