package com.amazon_backend.product_service.service.serviceImpl;


import com.amazon_backend.product_service.dto.ProductRequestDTO;
import com.amazon_backend.product_service.dto.ProductResponseDTO;
import com.amazon_backend.product_service.entity.Category;
import com.amazon_backend.product_service.entity.Product;
import com.amazon_backend.product_service.exception.ProductNotFoundException;
import com.amazon_backend.product_service.mapper.ProductMapper;
import com.amazon_backend.product_service.repository.CategoryRepository;
import com.amazon_backend.product_service.repository.ProductRepository;
import com.amazon_backend.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList = productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductResponseDTO responseDTO = new ProductResponseDTO();
                    responseDTO.setProductId(product.getProductId());
                    responseDTO.setProductDescription(product.getProductDescription());
                    responseDTO.setPrice(product.getPrice());
                    responseDTO.setQuantity(product.getQuantity());
                    responseDTO.setCategoryName(product.getCategory().getCategoryName());
                    responseDTO.setReviews(product.getReviews());
                    return responseDTO;
                })
                .toList();
        return productResponseDTOList;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        Product product = ProductMapper.toEntity(productRequestDTO);
        product.setProductName(productRequestDTO.getProductName());
        product.setProductName(productRequestDTO.getProductName());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        product.setProductDescription(productRequestDTO.getProductDescription());
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, UUID id) {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: " + id));
        product.setProductName(productRequestDTO.getProductName());
        product.setProductDescription(productRequestDTO.getProductDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        Product updatedProduct= productRepository.save(product);

        return ProductMapper.toResponseDTO(updatedProduct);
    }

    @Override
    public ProductResponseDTO updateProductQuantity(UUID id, int quantity) {
        Product product=  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        product.setQuantity(quantity);
        Product updatedProduct=productRepository.save(product);
        return ProductMapper.toResponseDTO(updatedProduct);
    }

    @Override
    public ProductResponseDTO updateProductPrice(UUID id, int price) {
        Product product=  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        product.setPrice(price);
        Product updatedProduct=productRepository.save(product);
        return ProductMapper.toResponseDTO(updatedProduct);
    }

    @Override
    public ProductResponseDTO updateProductDescription(UUID productId, String description) {
        Product product=  productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        product.setProductDescription(description);
        Product updatedProduct=productRepository.save(product);
        return ProductMapper.toResponseDTO(updatedProduct);
    }

    @Override
    public ProductResponseDTO updateProductCategory(UUID productId, UUID categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        Category category=categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ProductNotFoundException("Category not found with id: " + categoryId));

        product.setCategory(category);
        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toResponseDTO(updatedProduct);
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
        Product product=productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return ProductMapper.toResponseDTO(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
