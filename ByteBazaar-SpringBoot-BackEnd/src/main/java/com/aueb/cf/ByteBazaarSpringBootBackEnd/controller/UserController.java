package com.aueb.cf.ByteBazaarSpringBootBackEnd.controller;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserInsertDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.mapper.Mapper;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.repository.UserRepository;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing user-related endpoints.
 *
 * @author Chris
 * @version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves user information based on the provided JWT token.
     *
     * @param jwt The JWT token passed in the request header.
     * @return User information as a UserReadOnlyDTO.
     */
    @Operation(summary = "Find user by JWT")
    @GetMapping("/auth/users/profile")
    public UserReadOnlyDTO findUserByJwt(@RequestHeader("Authorization") String jwt) {
        try {
            UserReadOnlyDTO user = userService.findUserByJwt(jwt);
            return user;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null; // return null on exception
        }
    }

    /**
     * Creates a new user based on the provided UserInsertDTO.
     *
     * @param userDTO The UserInsertDTO containing user details.
     * @return Created user information as a UserReadOnlyDTO.
     */
    @Operation(summary = "Create a new user(Deprecated)")
    @PostMapping("/users")
    public UserReadOnlyDTO createUser(@Valid @RequestBody UserInsertDTO userDTO) {

        try {
            User userExists = userRepository.findByEmail(userDTO.getEmail());
            if (userExists != null) {
                throw new Exception("User with email " + userDTO.getEmail() + " exists");
            }

            User usernameExists = userRepository.findByUsername(userDTO.getUsername());
            if (usernameExists != null) {
                throw new Exception("User with username: " + userDTO.getUsername() + " exists");
            }

            User user = Mapper.mapToUser(userDTO);
            User savedUser = userRepository.save(user);
            return Mapper.mapToReadOnlyDTO(savedUser);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null; // return null on exception
        }
    }

    /**
     * Deletes a user based on the provided user ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return A message indicating success or an error message.
     */
    @Operation(summary = "Delete a user")
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        try {
            userRepository.deleteById(userId);
            return "User deleted successfully";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserReadOnlyDTO representing all users.
     */
    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public List<UserReadOnlyDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(Mapper::mapToReadOnlyDTO).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
