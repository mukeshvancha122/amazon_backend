package com.amazon_backend.product_service.repository;

import com.amazon_backend.product_service.entity.Category;
import com.amazon_backend.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface  ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Category> findCategoryById(UUID categoryId);
}
