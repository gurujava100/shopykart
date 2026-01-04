package com.shopykart.auth.service;

import com.shopykart.auth.dto.LoginRequestDTO;
import com.shopykart.auth.dto.LoginResponseDTO;
import com.shopykart.auth.jwt.JwtUtil;
import com.shopykart.exception.BadRequestException;
import com.shopykart.user.dto.UserRegisterRequest;
import com.shopykart.user.entity.User;
import com.shopykart.user.enums.Role;
import com.shopykart.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;   // 🔑 JWT

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // ✅ Generate JWT
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new LoginResponseDTO(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }

    @Override
    public void register(UserRegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email is already registered");
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
