package com.aueb.cf.ByteBazaarSpringBootBackEnd.service;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of {@link UserDetailsService}.
 * This service loads user-specific data for authentication.
 *
 * @author Chris
 * @version 1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user details based on the provided username (email).
     *
     * @param username The username (email) identifying the user.
     * @return A fully populated {@link UserDetails} object.
     * @throws UsernameNotFoundException If the user could not be found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email " + username);
        }

        // You can customize authorities (roles) here if needed.
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
