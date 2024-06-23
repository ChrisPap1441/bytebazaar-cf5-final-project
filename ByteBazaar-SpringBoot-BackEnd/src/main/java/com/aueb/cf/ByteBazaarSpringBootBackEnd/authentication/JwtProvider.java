package com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Provides utility methods for handling JSON Web Tokens (JWT) in authentication.
 * This class generates and parses JWTs for user authentication.
 *
 * @author Chris
 * @version 1.0
 */
@Service
public class JwtProvider {

    /**
     * The secret key used for signing and verifying JWTs.
     */
    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    /**
     * Generates a JWT based on the user's authentication.
     *
     * @param auth The authentication object containing user details.
     * @return The generated JWT.
     */
    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key).compact();

        return jwt;
    }

    /**
     * Extracts the email from a JWT token.
     *
     * @param jwt The JWT token.
     * @return The email associated with the token.
     */
    public String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
