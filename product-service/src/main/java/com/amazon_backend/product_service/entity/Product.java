package com.amazon_backend.product_service.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    @Column
    private String productName;

    @Column
    private String productDescription;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column
    private int price;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return productName;
    }

    public void setName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }





}
