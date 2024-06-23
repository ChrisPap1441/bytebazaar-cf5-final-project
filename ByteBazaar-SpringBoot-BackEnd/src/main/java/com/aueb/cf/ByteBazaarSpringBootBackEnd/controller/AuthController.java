package com.aueb.cf.ByteBazaarSpringBootBackEnd.controller;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication.JwtProvider;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication.request.LoginRequest;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.authentication.response.AuthResponse;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserInsertDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.repository.UserRepository;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.CustomUserDetailsService;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.InvalidPasswordException;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling user authentication-related endpoints.
 *
 * @author Chris
 * @version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetails;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user account.
     *
     * @param userDTO User data from the request body
     * @return Authentication response containing a JWT token
     * @throws Exception if there are errors during user creation or authentication
     */
    @Operation(summary = "Create a new user")
    @PostMapping("/signup")
    public AuthResponse createUser(@Valid @RequestBody UserInsertDTO userDTO) {

        AuthResponse res = new AuthResponse();
        try {
            String email = userDTO.getEmail();
            String password = userDTO.getPassword();
            String confirmPassword = userDTO.getConfirmPassword();
            String username = userDTO.getUsername();
//            String role = userDTO.getRole();

            // Check if email is already used
            User isExistEmail = userRepository.findByEmail(email);
            if (isExistEmail != null) {
                throw new Exception("Email is already used with another account");
            }

            // Check if username exists
            User usernameExists = userRepository.findByUsername(userDTO.getUsername());
            if (usernameExists != null) {
                throw new Exception("User with username: " + userDTO.getUsername() + " exists");
            }

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                throw new Exception("Passwords do not match.");
            }

            // Create a new user
            User createdUser = new User();
            createdUser.setEmail(email);
            createdUser.setPassword(passwordEncoder.encode(password));
            createdUser.setUsername(username);
//            createdUser.setRole(role);

            User savedUser = userRepository.save(createdUser);

            // Authenticate the user
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate a JWT token
            String token = jwtProvider.generateToken(authentication);

            res.setJwt(token);
            res.setMessage("signup success");
        } catch (Exception e) {
            res.setMessage("signup failed: " + e.getMessage());
        }

        return res;
    }

    /**
     * Handles user sign-in.
     *
     * @param loginRequest User login data from the request body
     * @return Authentication response containing a JWT token
     */
    @Operation(summary = "Sign in")
    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
        AuthResponse res = new AuthResponse();
        try {
            String username = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            // Authenticate the user
            Authentication authentication = authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate a JWT token
            String token = jwtProvider.generateToken(authentication);

            res.setJwt(token);
            res.setMessage("signin success");
        } catch (Exception e) {
            res.setMessage("signin failed: " + e.getMessage());
        }

        return res;
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username User's username (usually email)
     * @param password User's password
     * @return Authentication object if successful, or null if authentication fails
     */
    private Authentication authenticate(String username, String password) {
        UserDetails userDetails;
        try {
            // Load user details by username
            userDetails = customUserDetails.loadUserByUsername(username);

            if (userDetails == null) {
                throw new UserNotFoundException("user not found");
            }

            // Check if the provided password matches the stored password
            if(!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new InvalidPasswordException("invalid password");
            }
        } catch (UserNotFoundException e) {
            System.out.println("User not found: " + e.getMessage());
            return null;
        } catch (InvalidPasswordException e) {
            System.out.println("Invalid password: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }

        // Create an authentication token
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
