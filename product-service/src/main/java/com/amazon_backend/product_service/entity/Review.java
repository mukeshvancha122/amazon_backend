package com.amazon_backend.product_service.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;
@Entity
public class Review {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reviewId;

    private UUID userId;
    @Column
    private String reviewText;

    @Column
    private int rating;

    @Column
    private LocalDate createAt;

    @ManyToOne
    @JoinColumn
    private Product product;

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }


}
