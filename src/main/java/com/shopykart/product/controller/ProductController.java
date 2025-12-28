package com.shopykart.product.controller;

import com.shopykart.common.ApiResponse;
import com.shopykart.product.dto.ProductRequest;
import com.shopykart.product.dto.ProductResponse;
import com.shopykart.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        return ApiResponse.success(productService.createProduct(request));
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.success(productService.getAllProducts());
    }
}
