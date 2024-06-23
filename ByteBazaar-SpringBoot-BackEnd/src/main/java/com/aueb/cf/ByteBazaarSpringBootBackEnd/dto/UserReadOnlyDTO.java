package com.aueb.cf.ByteBazaarSpringBootBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for reading user information.
 * Represents a read-only view of user details.
 * Used for retrieving user information from the system.
 *
 * @author Chris
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    /**
     * Unique identifier for the user.
     */
    private Long id;

    /**
     * User's password.
     * Must be at least 8 characters long and meet complexity requirements.
     * Complexity requirements: At least one lowercase letter, one uppercase letter,
     * one digit, and one special character (@#$%^&+=).
     */
    private String password;

    /**
     * User's email address.
     */
    private String email;

    /**
     * User's chosen username.
     */
    private String username;

    /**
     * Role assigned to the user (e.g., admin, user).
     */
    private String role;

}
