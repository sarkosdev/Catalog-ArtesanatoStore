package com.artesanato.catalog.product.controller;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


    //Edits entirely an existing product using the ID to identify the product
    @PutMapping(path = "/update")
    public void putProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    // Edits partial an existing product (status and description) in this case
    @PatchMapping(path = "/partialUpdate/{productId}")
    public Product patchProduct(@PathVariable long productId, @RequestBody Map<String, Object> fields) {
        return productService.partialUpdateProduct(productId, fields);
    }

}
