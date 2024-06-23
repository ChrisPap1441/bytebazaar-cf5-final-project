package com.aueb.cf.ByteBazaarSpringBootBackEnd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user in the system.
 * This class encapsulates user-related information, including their credentials,
 * email address, username, and role within the application.
 *
 * <p>Users can be assigned different roles (e.g., admin, regular user) based on their privileges.</p>
 *
 * @author Chris
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The user's password (write-only).
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's role (e.g., admin, user).
     */
    private String role;
}
