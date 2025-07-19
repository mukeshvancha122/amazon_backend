package com.amazon_backend.product_service.repository;

import com.amazon_backend.product_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findCategoryByCategoryId(UUID categoryId);// Additional query methods can be defined here if needed
}
