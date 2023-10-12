package com.artesanato.catalog.product.controller;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    // Declarar
    private final ProductService productService;

    // Inicializar ProductService
    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    // Return all products
    @GetMapping
    public List<Product> getProducts() {
        return productService
                .getProducts();

    }

    // Return one single product by ID
    @GetMapping(path = "/get/product/{productId}")
    public Optional<Product> getSingleProduct(@PathVariable("productId") Long id) {
        return productService.findProductById(id);
    }

    // Creates one new product
    @PostMapping
    public void registerNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    // Creates new List of product
    //@RequestMapping(value = "/productList", method = RequestMethod.POST)
    @PostMapping(value = "/productList")
    public void registerListOfProducts(@RequestBody List<Product> productEntities) {
        productService.addListOfProduct(productEntities);
    }

    // Deletes an existing product by ID
    @DeleteMapping(path = "deleteById/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    // Deletes a List of products by ID passing a List of IDs
    @DeleteMapping(path = "deleteByIdList")
    public void deleteProductList(@RequestBody List<Long> idList) {
        for(Long id: idList) {
            productService.deleteProduct(id);
        }
    }

    //Edits entirely an existing product using the ID to identify the product
    @PutMapping(path = "/update")
    public void putProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    // Edits partial an existing product (status and description) in this case
    @PatchMapping(path = "/updateDescription/{productId}")
    public Product patchProductDescription(@PathVariable long productId, @RequestBody Map<String, String> fields) {
        return productService.updateProductDescription(productId, fields);
    }

    @PatchMapping(path = "/updateStatus/{productId}")
    public Product patchProductStatus(@PathVariable long productId, @RequestBody Map<String, Boolean> fields) {
        return productService.updateProductStatus(productId, fields);
    }
}
