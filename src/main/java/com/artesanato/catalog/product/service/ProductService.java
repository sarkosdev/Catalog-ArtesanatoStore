package com.artesanato.catalog.product.service;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

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

        logger.info("POST REQUEST | TRYING TO CREATE Product: {}" + product);

        Optional<Product> productOptional = productRepository
                .findProductByName(product.getName());


        // Validate that theres never 2 products with the same name
        if(productOptional.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        else {
            // Saves the student into DB
            productRepository.save(product);
            logger.info("Created product: {}", product);
        }
    }


    // Add List<Product> to DB
    public void addListOfProduct(List<Product> productEntities) {
        Set<Product> readyProductEntities = new HashSet<>();

        for(Product product : productEntities) {
            logger.info("POST REQUEST | TRYING TO CREATE List of Product: " + productEntities);

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
            existingProduct.setCategory(product.getCategory());

            // Saves in DB the object with new data
            productRepository.save(existingProduct);
            logger.info("Product updated with success: {}", existingProduct);
        }
        else {
            throw new IllegalStateException("PUT -> Product doesnt exist so it cant be updated");
        }
    }

    // VER COMO MELHORAR -----> A NAO SER USADO
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

    // Update product status
    public Product updateProductStatus(long id, Map<String, Boolean> fields) {
        logger.info("TRYING TO PATCH PRODUCT -> status");
        Product product = new Product();
        Optional<Product> existingProduct = productRepository.findById(id);

        if(existingProduct.isPresent()) {
            logger.info("Product before: ", existingProduct);

            if(fields.containsKey("status")) {
                Boolean status = fields.get("status");
                Product p = productRepository.findProductById(id);

                product.setId(p.getId());
                product.setName(p.getName());
                product.setDescription(p.getDescription());
                product.setCategory(p.getCategory());
                product.setHarvestingDate(p.getHarvestingDate());
                product.setStatus(status);

                productRepository.save(product);
                logger.info("Product after: ", product);
            }
            logger.info("SUCCESS ON PATCH Product status: {}");
        }
        else {
            throw new IllegalStateException("PATCH status -> Product doesnt exist so it cant be updated");
        }
        return product;
    }

    // Update Product description
    public Product updateProductDescription(long id, Map<String, String> fields) {
        logger.info("TRYING TO PATCH PRODUCT -> description");
        Product product = new Product();
        Optional<Product> existingProduct = productRepository.findById(id);

        if(existingProduct.isPresent()) {
            logger.info("Product before: ", existingProduct);

            if(fields.containsKey("description")) {
                String description = fields.get("description");
                Product p = productRepository.findProductById(id);

                product.setId(p.getId());
                product.setName(p.getName());
                product.setDescription(description);
                product.setCategory(p.getCategory());
                product.setHarvestingDate(p.getHarvestingDate());
                product.setStatus(p.getStatus());

                productRepository.save(product);
                logger.info("Product after: ", product);
            }
            logger.info("SUCCESS ON PATCH Product status: {}");
        }
        else {
            throw new IllegalStateException("PATCH description -> Product doesnt exist so it cant be updated");
        }
        return product;
    }

    // Returns one single product by ID
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
}
