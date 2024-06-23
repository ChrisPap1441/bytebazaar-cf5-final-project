package com.aueb.cf.ByteBazaarSpringBootBackEnd.service;

import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductInsertDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.ProductUpdateDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.dto.UserReadOnlyDTO;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.mapper.Mapper;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.Product;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.model.User;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.repository.ProductRepository;
import com.aueb.cf.ByteBazaarSpringBootBackEnd.service.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for product-related operations.
 *
 * @author Chris
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a new product.
     *
     * @param productDTO The data transfer object containing the product details.
     * @param userDTO The data transfer object containing the user details.
     * @return A {@link ProductReadOnlyDTO} representing the created product, or {@code null} if an error occurs.
     */
    @Override
    public ProductReadOnlyDTO createProduct(ProductInsertDTO productDTO, UserReadOnlyDTO userDTO) {
        try {
            User user = Mapper.mapToUser(userDTO);

            Product createdProduct = new Product();
            createdProduct.setTitle(productDTO.getTitle());
            createdProduct.setImage(productDTO.getImage());
            createdProduct.setDescription(productDTO.getDescription());
            createdProduct.setCategory(productDTO.getCategory());
            createdProduct.setContactInfo(productDTO.getContactInfo());
            createdProduct.setUser(user);
            createdProduct.setCreatedAt(LocalDateTime.now());

            Product productInserted = productRepository.save(createdProduct);

            return Mapper.mapToReadOnlyDTO(productInserted);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product to be found.
     * @return A {@link ProductReadOnlyDTO} representing the product with the given ID, or {@code null} if the product is not found.
     * @throws ProductNotFoundException if the product with the specified ID does not exist.
     */
    @Override
    public ProductReadOnlyDTO findProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                ProductReadOnlyDTO productDto = Mapper.mapToReadOnlyDTO(product.get());
                return productDto;
            } else {
                throw new ProductNotFoundException(id);
            }
        } catch (ProductNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to be deleted.
     * @throws ProductNotFoundException if the product with the specified ID does not exist.
     */
    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        ProductReadOnlyDTO productDto = findProductById(id);
        if (productDto == null) {
            throw new ProductNotFoundException(id);
        }
        Product product = Mapper.mapToProduct(productDto);
        productRepository.deleteById(product.getId());
    }

    /**
     * Updates an existing product.
     *
     * @param updateDTO The data transfer object containing the updated product details.
     * @param id The ID of the product to be updated.
     * @return A {@link ProductReadOnlyDTO} representing the updated product, or {@code null} if an error occurs.
     */
    @Override
    public ProductReadOnlyDTO updateProduct(ProductUpdateDTO updateDTO, Long id) {
        try {
            ProductReadOnlyDTO productDto = findProductById(id);
            Product oldProduct = Mapper.mapToProduct(productDto);

            if (updateDTO.getTitle() != null) {
                oldProduct.setTitle(updateDTO.getTitle());
            }

            if (updateDTO.getImage() != null) {
                oldProduct.setImage(updateDTO.getImage());
            }

            if (updateDTO.getDescription() != null) {
                oldProduct.setDescription(updateDTO.getDescription());
            }

            if (updateDTO.getCategory() != null) {
                oldProduct.setCategory(updateDTO.getCategory());
            }

            if (updateDTO.getContactInfo() != null) {
                oldProduct.setContactInfo(updateDTO.getContactInfo());
            }

            Product updatedProduct = productRepository.save(oldProduct);

            return Mapper.mapToReadOnlyDTO(updatedProduct);
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all products.
     *
     * @return A list of {@link ProductReadOnlyDTO} representing all products, or {@code null} if an error occurs.
     */
    @Override
    public List<ProductReadOnlyDTO> findAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            return products.stream().map(Mapper::mapToReadOnlyDTO).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving products: " + e.getMessage());
            return null;
        }
    }

    /**
     * Toggles the liked status of a product for a user.
     *
     * @param productId The ID of the product to be liked or unliked.
     * @param userDTO The data transfer object containing the user details.
     * @return A {@link ProductReadOnlyDTO} representing the updated product with the new liked status, or {@code null} if an error occurs.
     */
    @Override
    public ProductReadOnlyDTO likedProduct(Long productId, UserReadOnlyDTO userDTO) {
        try {
            ProductReadOnlyDTO productDto = findProductById(productId);
            Product product = Mapper.mapToProduct(productDto);

            User user = Mapper.mapToUser(userDTO);

            if (product.getLikes().contains(user.getId())) {
                product.getLikes().remove(user.getId());
            } else {
                product.getLikes().add(user.getId());
            }
            Product updatedProduct = productRepository.save(product);

            return Mapper.mapToReadOnlyDTO(updatedProduct);
        } catch (Exception e) {
            System.out.println("Error updating liked status: " + e.getMessage());
            return null;
        }
    }
}
