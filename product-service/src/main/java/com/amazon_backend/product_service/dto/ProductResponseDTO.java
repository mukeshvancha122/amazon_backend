package com.amazon_backend.product_service.dto;

import com.amazon_backend.product_service.entity.Review;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class ProductResponseDTO {

    @NotNull
    private String productId;

    @NotNull
    private String productDescription;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @NotNull
    private String CategoryName;

    @NotNull
    private List<Review> reviews;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
