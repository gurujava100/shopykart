package com.shopykart.product.service;

import com.shopykart.product.dto.ProductRequest;
import com.shopykart.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();
}
