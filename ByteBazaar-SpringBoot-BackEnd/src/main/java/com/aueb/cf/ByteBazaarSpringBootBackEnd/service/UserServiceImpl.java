package com.aueb.cf.ByteBazaarSpringBootBackEnd.service;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication.JwtProvider;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.mapper.Mapper;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.repository.UserRepository;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.InvalidJwtTokenException;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for user-related operations.
 *
 * @author Chris
 * @version 1.0
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Finds a user by their ID.
     *
     * @param userId The ID of the user to be found.
     * @return A {@link UserReadOnlyDTO} representing the user with the given ID, or {@code null} if the user is not found.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    @Override
    public UserReadOnlyDTO findUserById(Long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                UserReadOnlyDTO userDTO = Mapper.mapToReadOnlyDTO(user.get());
                return userDTO;
            } else {
                throw new UserNotFoundException(userId);
            }
        } catch (UserNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    /**
     * Finds a user by their JWT token.
     *
     * @param jwt The JWT token containing the user's information.
     * @return A {@link UserReadOnlyDTO} representing the user associated with the given JWT token, or {@code null} if the user is not found or the JWT token is invalid.
     * @throws InvalidJwtTokenException if the JWT token is invalid.
     * @throws UserNotFoundException if the user associated with the email extracted from the JWT token does not exist.
     */
    @Override
    public UserReadOnlyDTO findUserByJwt(String jwt) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            if (email == null) {
                throw new InvalidJwtTokenException("Provide a valid JWT token");
            }

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UserNotFoundException("User not found with email " + email);
            }

            UserReadOnlyDTO userDTO = Mapper.mapToReadOnlyDTO(user);
            return userDTO;
        } catch (InvalidJwtTokenException | UserNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }
}