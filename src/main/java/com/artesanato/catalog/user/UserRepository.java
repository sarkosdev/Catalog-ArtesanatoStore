package com.artesanato.catalog.user;

import com.artesanato.catalog.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{

    // SELECT * from User Where name = X
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findUserByName(String name);

    // SELECT * from User Where id = x
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(Long id);

    // SELECT * from User Where name = X
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findUserInDBByName(String name);

}
