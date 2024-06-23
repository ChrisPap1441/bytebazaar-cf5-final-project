package com.aueb.cf.ByteBazaarSpringBootBackEnd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a product in the system.
 * Each product has various attributes such as title, user, image, description, and category.
 * Products can be associated with users and receive likes from other users.
 *
 * @author Chris
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Title of the product.
     */
    private String title;

    /**
     * User associated with the product.
     */
    @ManyToOne
    private User user;

    /**
     * URL image data representing the product.
     */
    private String image;

    /**
     * Description of the product.
     */
    @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
    private String description;

    /**
     * Category of the product (e.g., electronics, etc.).
     */
    private String category;

    /**
     * Contact information for inquiries about the product.
     */
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
