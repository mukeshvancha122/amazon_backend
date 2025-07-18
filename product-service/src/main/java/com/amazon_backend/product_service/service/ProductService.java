package com.amazon_backend.product_service.service;

import com.amazon_backend.product_service.dto.ProductRequestDTO;
import com.amazon_backend.product_service.dto.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {


     List<ProductResponseDTO> getAllProducts();
     ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
     ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, UUID id);
     ProductResponseDTO updateProductQuantity(UUID id, int quantity);
     ProductResponseDTO updateProductPrice(UUID id, int price);
     ProductResponseDTO updateProductDescription(UUID productId,String description);
     ProductResponseDTO updateProductCategory(UUID productId, UUID categoryId);
     ProductResponseDTO getProductById(UUID id);
     void deleteProduct(UUID id);
}
