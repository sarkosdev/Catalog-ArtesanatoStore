package com.artesanato.catalog.user;

import com.artesanato.catalog.cart.Cart;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_role", nullable = false)
    private String userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cart> carts;


    public User() {
    }

    public User(String name, String email, String userRole) {
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }

    public User(Long id, String name, String email, String userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserRole() {
        return userRole;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    // Object to String
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
