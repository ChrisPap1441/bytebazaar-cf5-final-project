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
 * Data Transfer Object (DTO) for updating a product.
 * Represents product information received from the client during updating.
 * Used for updating product entries in the system.
 *
 * @author Chris
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateDTO {

    /**
     * Unique identifier for the product.
     */
    private Long id;

    /**
     * Title of the product.
     */
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 25, message = "Title must be between 3 and 25 characters")
    private String title;

    /**
     * User associated with the product.
     */
    private User user;

    /**
     * URL image data representing the product.
     */
    @NotEmpty
    @NotBlank
    private String image;

    /**
     * Description of the product.
     */
    @NotEmpty
    @NotBlank
    @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
    private String description;

    /**
     * Category of the product (e.g., electronics, etc.).
     */
    @NotEmpty
    @NotBlank
    private String category;

    /**
     * Contact information for inquiries about the product.
     */
    @NotEmpty
    @NotBlank
    @Size(max = 200, message = "Contact Info must be less than or equal to 200 characters")
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
