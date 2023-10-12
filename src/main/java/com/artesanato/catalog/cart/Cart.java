package com.artesanato.catalog.cart;

import com.artesanato.catalog.product.Product;
import com.artesanato.catalog.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @SequenceGenerator(name = "cart_sequence", sequenceName = "cart_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_sequence")
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "cart_name")
    private String name;

    @Column(name = "cart_status", nullable = false)
    private Boolean status;

    @ManyToMany
    private Set<Product> productSet = new HashSet<Product>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Cart() {
    }

    public Cart(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cart(Long id, Boolean status ,String name, User user) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.user = user;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", productSet=" + productSet +
                ", user=" + user +
                '}';
    }
}
