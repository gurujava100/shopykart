package com.shopykart.auth.controller;

import com.shopykart.auth.dto.LoginRequestDTO;
import com.shopykart.auth.service.AuthService;
import com.shopykart.common.ApiResponse;
import com.shopykart.user.dto.UserRegisterRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequestDTO request) {
        return ApiResponse.success(authService.login(request));
    }
    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody UserRegisterRequest request) {
        authService.register(request);
        return ApiResponse.success("User registered successfully");
    }


}
