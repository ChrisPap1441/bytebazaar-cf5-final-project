package com.aueb.cf.ByteBazaarSpringBootBackEnd.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for updating a new user.
 * Represents user information received from the client during updating.
 * Used for updating user entries in the system.
 *
 * @author Chris
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

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
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", message = "Password must meet complexity requirements")
    @NotEmpty
    @NotBlank
    private String password;

    /**
     * User's email address.
     */
    @NotEmpty
    @NotBlank
    private String email;

    /**
     * User's chosen username.
     */
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    private String username;

    /**
     * Role assigned to the user (e.g., admin, user).
     */
    private String role;
}
