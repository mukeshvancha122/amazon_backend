package com.amazon_backend.product_service.controller;

import com.amazon_backend.product_service.dto.ProductRequestDTO;
import com.amazon_backend.product_service.dto.ProductResponseDTO;
import com.amazon_backend.product_service.service.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl ProductServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> createProduct(ProductRequestDTO productRequestDTO){
        ProductResponseDTO product = ProductServiceImpl.createProduct(productRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequestDTO productRequestDTO,@PathVariable("id") UUID id) {
        ProductResponseDTO updatedProduct = ProductServiceImpl.updateProduct(productRequestDTO, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/update/quantity/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductQuantity(@PathVariable("id") UUID id, int quantity) {
        ProductResponseDTO updatedProduct = ProductServiceImpl.updateProductQuantity(id, quantity);
        return ResponseEntity.ok(updatedProduct);
    }
    @PostMapping("/update/price/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductPrice(@PathVariable("id") UUID id,@RequestBody int price) {
        ProductResponseDTO updatedProduct = ProductServiceImpl.updateProductPrice(id, price);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/update/description/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProductDescription(@PathVariable("productId") UUID productId, @RequestBody String description) {
        ProductResponseDTO updatedProduct = ProductServiceImpl.updateProductDescription(productId, description);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/update/category/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProductCategory(@PathVariable("productId") UUID productId, @RequestBody UUID categoryId) {
        ProductResponseDTO updatedProduct = ProductServiceImpl.updateProductCategory(productId, categoryId);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") UUID id) {
        ProductResponseDTO product = ProductServiceImpl.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) {
        ProductServiceImpl.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductServiceImpl.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch products: " + e.getMessage());
        }
    }



}
