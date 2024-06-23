package com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an authentication response containing a JWT (JSON Web Token) and an optional message.
 * This class is used to send authentication-related information back to the client.
 *
 * @author Chris
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    /**
     * The JSON Web Token (JWT) generated during authentication.
     */
    private String jwt;

    /**
     * An optional message (e.g., success message, error message) related to the authentication process.
     */
    private String message;
}
