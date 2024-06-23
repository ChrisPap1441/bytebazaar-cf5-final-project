package com.aueb.cf.ByteBazaarSpringBootBackEnd.repository;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Product} entities.
 * Provides CRUD (Create, Read, Update, Delete) operations for products.
 *
 * @author Chris
 * @version 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
