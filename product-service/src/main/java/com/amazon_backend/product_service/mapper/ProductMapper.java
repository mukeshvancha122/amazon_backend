package com.amazon_backend.product_service.mapper;

import com.amazon_backend.product_service.dto.ProductRequestDTO;
import com.amazon_backend.product_service.dto.ProductResponseDTO;
import com.amazon_backend.product_service.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setName(productRequestDTO.getProductName());
        product.setPrice(productRequestDTO.getPrice());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setQuantity(productRequestDTO.getQuantity());
        return product;
    }

    public static ProductResponseDTO toResponseDTO(Product product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(product.getProductId());
        productResponseDTO.setProductDescription(product.getProductDescription());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setQuantity(product.getQuantity());
        productResponseDTO.setCategoryName(product.getCategory().getCategoryName());
        return productResponseDTO;
    }
}
