package com.artesanato.catalog.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // SELECT * from Product Where name = X
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findProductByName(String name);

    // SELECT * from Product Where id = x
    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Product findProductById(Long id);

    //SELECT * from Product Where name = x
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Product findProductInDBByName(String name);
}
