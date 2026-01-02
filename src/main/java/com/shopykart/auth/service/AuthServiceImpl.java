package com.shopykart.auth.service;

import com.shopykart.auth.dto.LoginRequestDTO;
import com.shopykart.auth.dto.LoginResponseDTO;
import com.shopykart.exception.BadRequestException;
import com.shopykart.user.dto.UserRegisterRequest;
import com.shopykart.user.dto.UserResponseDTO;
import com.shopykart.user.entity.User;
import com.shopykart.user.enums.RegistrationType;
import com.shopykart.user.repository.UserRepository;
import   com.shopykart.exception.GlobalExceptionHandler;
import com.shopykart.user.service.strategy.UserRegistrationStrategy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shopykart.user.enums.Role;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Service

public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public LoginResponseDTO login(LoginRequestDTO request)  {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }
        String token = "TEMP_TOKEN";

        return new LoginResponseDTO(
                token,
                user.getEmail(),
                "USER"
        );
    }

    @Override
    public void register(UserRegisterRequest request) {
        // Check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already registered");
        }

        // Create user entity
        User user = new User(
                request.getName(),
                request.getEmail(),

                passwordEncoder.encode(request.getPassword()) // encrypt password
        );
        user.setRole(Role.USER);
        // Save user
        userRepository.save(user);
    }


}
