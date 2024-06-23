package com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a login request containing user credentials.
 * This class is used to send login information (email and password) to the server.
 *
 * @author Chris
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's password.
     */
    private String password;
}
