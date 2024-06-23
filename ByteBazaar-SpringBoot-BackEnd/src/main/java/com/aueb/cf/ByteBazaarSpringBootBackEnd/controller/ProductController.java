package com.aueb.cf.ByteBazaarSpringBootBackEnd.controller;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductInsertDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductUpdateDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.IProductService;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.IUserService;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing product-related endpoints.
 *
 * @author Chris
 * @version 1.0
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    /**
     * Creates a new product.
     *
     * @param productDTO Product data from the request body
     * @param jwt Authorization token from the request header
     * @return Read-only representation of the created product
     */
    @Operation(summary = "Create a new product")
    @PostMapping()
    public ProductReadOnlyDTO createProduct(@Valid @RequestBody ProductInsertDTO productDTO, @RequestHeader("Authorization") String jwt) {
        ProductReadOnlyDTO createdProduct;
        try {
            UserReadOnlyDTO userDTO = userService.findUserByJwt(jwt);

            // Create the product and associate it with the user
            createdProduct = productService.createProduct(productDTO, userDTO);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null; // return null on exception
        }

        return createdProduct;
    }

    /**
     * Updates an existing product.
     *
     * @param updateDTO Product data from the request body
     * @param id        Product ID to update
     * @return Read-only representation of the updated product
     */
    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    public ProductReadOnlyDTO updateProduct(@Valid @RequestBody ProductUpdateDTO updateDTO, @PathVariable Long id) {
        ProductReadOnlyDTO updatedProduct;
        try {
            updatedProduct = productService.updateProduct(updateDTO, id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null; // return null on exception
        }

        return updatedProduct;
    }

    /**
     * Retrieves a list of all products.
     *
     * @return List of read-only product representations
     */
    @Operation(summary = "Get all products")
    @GetMapping()
    public List<ProductReadOnlyDTO> getAllProducts() {
        List<ProductReadOnlyDTO> products;
        try {
            products = productService.findAllProducts();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            products = new ArrayList<>(); // return an empty list on exception
        }

        return products;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId ID of the product to delete
     * @return A message indicating the result of the deletion
     */
    @Operation(summary = "Delete a product")
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        String response;
        try {
            productService.deleteProduct(productId);
            response = "Product deleted successfully";
        } catch (ProductNotFoundException e) {
            response = "Product not found";
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            response = "Product deletion failed";
        }
        return response;
    }

    /**
     * Likes a product.
     *
     * @param jwt Authorization token from the request header
     * @param id  ID of the product to like
     * @return Read-only representation of the updated product
     */
    @Operation(summary = "Like a product")
    @PutMapping("/{id}/like")
    public ProductReadOnlyDTO likeProduct(@RequestHeader("Authorization") String jwt, @PathVariable Long id) {
        ProductReadOnlyDTO updatedProduct;
        try {
            UserReadOnlyDTO dto = userService.findUserByJwt(jwt);

            updatedProduct = productService.likedProduct(id, dto);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }

        return updatedProduct;
    }
}
