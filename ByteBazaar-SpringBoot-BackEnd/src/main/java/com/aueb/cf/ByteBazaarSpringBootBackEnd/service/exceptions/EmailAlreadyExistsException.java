package com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions;

import java.io.Serial;

/**
 * Exception thrown when an attempt is made to register or use an email that already exists in the system.
 *
 * @author Chris
 * @version 1.0
 */
public class EmailAlreadyExistsException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new EmailAlreadyExistsException with the specified email.
     *
     * @param email The email that already exists.
     */
    public EmailAlreadyExistsException(String email) {
        super("Email :" + email + " already exists");
    }
}
