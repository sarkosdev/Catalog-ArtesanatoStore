package com.artesanato.catalog.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // SELECT * from Cart Where name = x
    @Query("SELECT c FROM Cart c WHERE c.name = ?1")
    Optional<Cart> findCartByName(String name);

    // SELECT * from Cart Where id = x
    @Query("SELECT c FROM Cart c WHERE c.id = ?1")
    Cart findCartInDBByID(Long id);

}
