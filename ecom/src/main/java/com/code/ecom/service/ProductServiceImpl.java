package com.code.ecom.service;

import com.code.ecom.dto.ProductResponse;
import com.code.ecom.dto.ProductRequest;
import com.code.ecom.entity.Product;
import com.code.ecom.mapper.ProductMapper;
import com.code.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);

        return productMapper.toProductResponse(savedProduct);
    }

    @Override
    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id).map(product -> {
            productMapper.updateProductFromProductRequest(productRequest, product);
            return productMapper.toProductResponse(productRepository.save(product));
        });
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findByActiveTrue().stream().map(productMapper::toProductResponse).toList();
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.findById(id).map(product -> {
            product.setActive(false);
            productRepository.save(product);
            return true;
        }).orElse(false);
    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream().map(productMapper::toProductResponse).toList();
    }
}
