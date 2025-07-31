package com.code.ecom.service;

import com.code.ecom.dto.ProductResponse;
import com.code.ecom.dto.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    boolean deleteProduct(Long id);

    List<ProductResponse> searchProducts(String keyword);
}
