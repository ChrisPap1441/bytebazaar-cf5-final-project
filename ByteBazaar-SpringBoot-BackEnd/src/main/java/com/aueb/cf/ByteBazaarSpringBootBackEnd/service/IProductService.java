package com.aueb.cf.ByteBazaarSpringBootBackEnd.service;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductInsertDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductUpdateDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.ProductNotFoundException;

import java.util.List;

/**
 * Service interface for managing products within the application.
 * Provides methods for creating, retrieving, updating, and deleting products.
 * Additionally, it handles product-related operations such as liking a product.
 *
 * @author Chris
 * @version 1.0
 */
public interface IProductService {

    /**
     * Creates a new product.
     *
     * @param productDTO The product data to create.
     * @param userDTO    The user creating the product.
     * @return The created product details.
     */
    public ProductReadOnlyDTO createProduct(ProductInsertDTO productDTO, UserReadOnlyDTO userDTO);

    /**
     * Finds a product by its ID.
     *
     * @param id The product ID.
     * @return The product details.
     * @throws ProductNotFoundException If the product is not found.
     */
    public ProductReadOnlyDTO findProductById(Long id) throws ProductNotFoundException;

    /**
     * Deletes a product by its ID.
     *
     * @param id The product ID to delete.
     * @throws Exception If an error occurs during deletion.
     */
    public void deleteProduct(Long id) throws Exception;

    /**
     * Updates a product by its ID.
     *
     * @param updateDTO The updated product data.
     * @param id        The product ID to update.
     * @return The updated product details.
     * @throws Exception If an error occurs during update.
     */
    public ProductReadOnlyDTO updateProduct(ProductUpdateDTO updateDTO, Long id) throws Exception;

    /**
     * Retrieves a list of all products.
     *
     * @return A list of product details.
     */
    public List<ProductReadOnlyDTO> findAllProducts();

    /**
     * Likes a product by its ID.
     *
     * @param productId The product ID to like.
     * @param userDTO   The user liking the product.
     * @return The updated product details after liking.
     * @throws Exception If an error occurs during liking.
     */
    public ProductReadOnlyDTO likedProduct(Long productId, UserReadOnlyDTO userDTO) throws Exception;
}