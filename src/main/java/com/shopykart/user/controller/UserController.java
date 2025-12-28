package com.shopykart.user.controller;

import com.shopykart.user.dto.UserRegisterRequest;
import com.shopykart.user.enums.RegistrationType;
import com.shopykart.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody @Valid UserRegisterRequest request
    ) {
        userService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}
