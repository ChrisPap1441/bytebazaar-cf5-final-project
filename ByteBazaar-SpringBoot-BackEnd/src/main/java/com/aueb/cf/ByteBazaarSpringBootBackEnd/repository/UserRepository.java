package com.aueb.cf.ByteBazaarSpringBootBackEnd.repository;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link User} entities.
 * Provides CRUD (Create, Read, Update, Delete) operations for users.
 *
 * <p>Users can be retrieved by their email address or username.</p>
 *
 * @author Chris
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email The user's email address.
     * @return The user with the specified email address, or null if not found.
     */
    public User findByEmail(String email);

    /**
     * Finds a user by their username.
     *
     * @param username The user's username.
     * @return The user with the specified username, or null if not found.
     */
    public User findByUsername(String username);
}
