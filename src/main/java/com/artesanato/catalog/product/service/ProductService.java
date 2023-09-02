package com.artesanato.catalog.product.service;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // Adds new Product in to database
    public void addNewProduct(Product product) {

        System.out.println("POST REQUEST | TRYING TO CREATE Product: " + product);

        Optional<Product> productOptional = productRepository
                .findProductByName(product.getName());


        // Validate that theres never 2 products with the same name
        if(productOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        else {
            // Saves the student into DB
            productRepository.save(product);
        }
    }


    // Add List<Product> to DB
    public void addListOfProduct(List<Product> productEntities) {
        Set<Product> readyProductEntities = new HashSet<>();

        for(Product product : productEntities) {
            System.out.println("POST REQUEST | TRYING TO CREATE List of Product: " + productEntities);

            Optional<Product> productOptional = productRepository
                    .findProductByName(product.getName());

            // Validate that theres never 2 products with the same name
            if(productOptional.isPresent()) {
                //throw new IllegalStateException("name taken already");
                logger.error("Failled to create product name not unique: {}", product);
            }
            else {
                // Saves the student into DB
                //productRepository.save(product);
                readyProductEntities.add(product);
            }
        }

        for(Product product : readyProductEntities) {
            productRepository.save(product);
            logger.info("Created product: {}", product);
        }

    }

    // Deletes a product from DB by ID
    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists) {
            throw new IllegalStateException("product with id: " + productId + "doesnt exist");
        }
        productRepository.deleteById(productId);
        System.out.println("SUCCESS DELETING PRODUCT WITH ID: " + productId);
    }


    // Updates the product entirely according to an ID
    public void updateProduct(Product product) {

        Optional<Product> existingProductEntityOptional = productRepository.findById(product.getId());

        // Check if the product exists before performing an update
        if(existingProductEntityOptional.isPresent()) {
            // Get the product that exists in the DB
            Product existingProduct = existingProductEntityOptional.get();

            // Update all the properties of the existing product
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setHarvestingDate(product.getHarvestingDate());
            existingProduct.setStatus(product.getStatus());

            // Saves in DB the object with new data
            productRepository.save(existingProduct);
            logger.info("Product updated with success: {}", existingProduct);
        }
        else {
            throw new IllegalStateException("PUT -> Product doesnt exist so it cant be updated");
        }
    }

    // VER COMO MELHORAR
    // It only updates "description" field because its set to update Strings
    public Product partialUpdateProduct(long id, Map<String, Object> fields) {
        // Checks if the product exists in the DB
        Optional<Product> existingProduct = productRepository.findById(id);

        if(existingProduct.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct.get(), value);
            });
            return productRepository.save(existingProduct.get());
        }
        else {
            throw new IllegalStateException("PATCH -> Product doesnt exist so it cant be updated");
        }

    }
}
