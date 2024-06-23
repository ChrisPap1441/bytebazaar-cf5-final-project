package com.aueb.cf.ByteBazaarSpringBootBackEnd.dto;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) for reading product information.
 * Represents a read-only view of product details.
 * Used for retrieving product information from the system.
 *
 * @author Chris
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductReadOnlyDTO {

    /**
     * Unique identifier for the product.
     */
    private Long id;

    /**
     * Title of the product.
     */
    private String title;

    /**
     * User associated with the product.
     */
    private User user;

    /**
     * URL image data representing the product.
     */
    private String image;

    /**
     * Description of the product.
     */
    private String description;

    /**
     * Category of the product (e.g., electronics, etc.).
     */
    private String category;

    /**
     * Contact information for inquiries about the product.
     */
    private String contactInfo;

    /**
     * Date and time when the product was created.
     */
    private LocalDateTime createdAt;

    /**
     * List of user IDs who have liked this product.
     */
    private List<Long> likes = new ArrayList<>();
}
