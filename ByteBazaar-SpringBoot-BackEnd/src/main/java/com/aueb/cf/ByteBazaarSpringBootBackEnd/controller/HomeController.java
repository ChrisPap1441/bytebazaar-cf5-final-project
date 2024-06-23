package com.aueb.cf.ByteBazaarSpringBootBackEnd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the home endpoint.
 *
 * @author Chris
 * @version 1.0
 */
@RestController
public class HomeController {

    /**
     * Handles requests to the root endpoint.
     *
     * @return A welcome message for the ByteBazaar application
     */
    @GetMapping
    public String homeController() {
        return "CF5 Final Project: ByteBazaar by Chris Papadopoulos";
    }
}
