package com.artesanato.catalog.product;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String name;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Column(name = "product_harvesting_date", nullable = false)
    private LocalDate harvestingDate;

    @Column(name = "product_status", nullable = false)
    private Boolean status;

    @Column(name = "product_category", nullable = false)
    private String category;

    public Product() {

    }

    public Product(Long id, String name, String description, LocalDate harvestingDate, Boolean status, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.harvestingDate = harvestingDate;
        this.status = status;
        this.category = category;
    }

    public Product(Long id, String name, String description, LocalDate harvestingDate, Boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.harvestingDate = harvestingDate;
        this.status = status;
    }


    public Product(String name, String description, LocalDate harvestingDate, Boolean status, String category) {
        this.name = name;
        this.description = description;
        this.harvestingDate = harvestingDate;
        this.status = status;
        this.category = category;
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

    public String getCategory() {
        return category;
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

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", harvestingDate=" + harvestingDate +
                ", status=" + status +
                ", category='" + category + '\'' +
                '}';
    }
}
