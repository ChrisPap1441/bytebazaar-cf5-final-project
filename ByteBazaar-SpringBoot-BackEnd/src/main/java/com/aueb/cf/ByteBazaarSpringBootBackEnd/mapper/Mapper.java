package com.aueb.cf.ByteBazaarSpringBootBackEnd.mapper;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.*;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.Product;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;

/**
 * Utility class for mapping between DTOs (Data Transfer Objects) and domain entities.
 * Provides methods to convert between different representations of user and product data.
 *
 * @author Chris
 * @version 1.0
 */
public class Mapper {

    // Private constructor to prevent instantiation
    private Mapper() {}

    /**
     * Maps a {@link User} entity to a read-only DTO.
     *
     * @param user The user entity to map.
     * @return A read-only DTO representing the user.
     */
    public static UserReadOnlyDTO mapToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getPassword(), user.getEmail(), user.getUsername(), user.getRole());
    }

    /**
     * Maps a {@link UserInsertDTO} to a {@link User} entity.
     *
     * @param dto The user insert DTO.
     * @return A user entity created from the DTO.
     */
    public static User mapToUser(UserInsertDTO dto) {
        return new User(dto.getId(), dto.getPassword(), dto.getEmail(), dto.getUsername(), dto.getRole());
    }

    /**
     * Maps a {@link UserUpdateDTO} to a {@link User} entity.
     *
     * @param dto The user update DTO.
     * @return An updated user entity.
     */
    public static User mapToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getPassword(), dto.getEmail(), dto.getUsername(), dto.getRole());
    }

    /**
     * Maps a {@link UserReadOnlyDTO} to a {@link User} entity.
     *
     * @param dto The read-only DTO representing a user.
     * @return A user entity created from the DTO.
     */
    public static User mapToUser(UserReadOnlyDTO dto) {
        return new User(dto.getId(), dto.getPassword(), dto.getEmail(), dto.getUsername(), dto.getRole());
    }

    /**
     * Maps a {@link Product} entity to a read-only DTO.
     *
     * @param product The product entity to map.
     * @return A read-only DTO representing the product.
     */
    public static ProductReadOnlyDTO mapToReadOnlyDTO(Product product) {
        return new ProductReadOnlyDTO(product.getId(), product.getTitle(), product.getUser(), product.getImage(), product.getDescription(),
                product.getCategory(), product.getContactInfo(), product.getCreatedAt(), product.getLikes());
    }

    /**
     * Maps a {@link ProductInsertDTO} to a {@link Product} entity.
     *
     * @param dto The product insert DTO.
     * @return A product entity created from the DTO.
     */
    public static Product mapToProduct(ProductInsertDTO dto) {
        return new Product(dto.getId(), dto.getTitle(), dto.getUser(), dto.getImage(), dto.getDescription(),
                dto.getCategory(), dto.getContactInfo(), dto.getCreatedAt(), dto.getLikes());
    }

    /**
     * Maps a {@link ProductUpdateDTO} to a {@link Product} entity.
     *
     * @param dto The product update DTO.
     * @return An updated product entity.
     */
    public static Product mapToProduct(ProductUpdateDTO dto) {
        return new Product(dto.getId(), dto.getTitle(), dto.getUser(), dto.getImage(), dto.getDescription(),
                dto.getCategory(), dto.getContactInfo(), dto.getCreatedAt(), dto.getLikes());
    }

    /**
     * Maps a {@link ProductReadOnlyDTO} to a {@link Product} entity.
     *
     * @param dto The read-only DTO representing a product.
     * @return A product entity created from the DTO.
     */
    public static Product mapToProduct(ProductReadOnlyDTO dto) {
        return new Product(dto.getId(), dto.getTitle(), dto.getUser(), dto.getImage(), dto.getDescription(),
                dto.getCategory(), dto.getContactInfo(), dto.getCreatedAt(), dto.getLikes());
    }
}
