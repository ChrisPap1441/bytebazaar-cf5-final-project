package com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication;

/**
 * Contains constants related to JSON Web Tokens (JWT) used for authentication.
 * This class provides the JWT secret and header name.
 *
 * @author Chris
 * @version 1.0
 */
public class JwtConstant {

    /**
     * The secret key used for signing and verifying JWTs.
     */
    public static final String JWT_SECRET = "P4lO9cXr8Nt1wK5q7ZHS2yF6bE0dxU3mJ8v";

    /**
     * The HTTP header name where the JWT is included (e.g., in an HTTP request).
     */
    public static final String JWT_HEADER = "Authorization";
}
