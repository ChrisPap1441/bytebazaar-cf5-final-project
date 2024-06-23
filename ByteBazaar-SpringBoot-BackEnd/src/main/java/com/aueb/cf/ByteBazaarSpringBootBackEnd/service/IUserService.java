package com.aueb.cf.ByteBazaarSpringBootBackEnd.service;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.InvalidJwtTokenException;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.UserNotFoundException;

/**
 * Service interface for managing user-related operations.
 * Provides methods for retrieving user details by ID or JWT (JSON Web Token).
 *
 * @author Chris
 * @version 1.0
 */
public interface IUserService {

    /**
     * Finds a user by their ID.
     *
     * @param userId The user ID.
     * @return The user details.
     * @throws UserNotFoundException If the user is not found.
     */
    public UserReadOnlyDTO findUserById(Long userId) throws UserNotFoundException;

    /**
     * Finds a user by their JWT (JSON Web Token).
     *
     * @param jwt The JWT representing the user.
     * @return The user details.
     * @throws UserNotFoundException    If the user is not found.
     * @throws InvalidJwtTokenException If the JWT is invalid.
     */
    public UserReadOnlyDTO findUserByJwt(String jwt) throws UserNotFoundException, InvalidJwtTokenException;
}
