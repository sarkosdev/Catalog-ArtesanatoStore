package com.artesanato.catalog.product;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    private String name;
    private String description;
    private LocalDate harvestingDate;
    private Boolean status;

    public Product() {

    }

    public Product(Long id, String name, String description, LocalDate harvestingDate, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.harvestingDate = harvestingDate;
        this.status = status;
    }


    public Product(String name, String description, LocalDate harvestingDate, Boolean status) {
        this.name = name;
        this.description = description;
        this.harvestingDate = harvestingDate;
        this.status = status;
    }


    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getHarvestingDate() {
        return harvestingDate;
    }

    public Boolean getStatus() {
        return status;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHarvestingDate(LocalDate harvestingDate) {
        this.harvestingDate = harvestingDate;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", harvestingDate=" + harvestingDate +
                ", status=" + status +
                '}';
    }
}
