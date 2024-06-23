package com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions;

import java.io.Serial;

/**
 * Exception thrown when a product with a specified ID is not found.
 *
 * @author Chris
 * @version 1.0
 */
public class ProductNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ProductNotFoundException with the specified product ID.
     *
     * @param id The ID of the product that was not found.
     */
    public ProductNotFoundException(Long id) {
        super("Product with id: " + id + " not found!");
    }
}
