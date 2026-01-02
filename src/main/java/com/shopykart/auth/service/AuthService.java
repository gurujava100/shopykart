package com.shopykart.auth.service;

import com.shopykart.auth.dto.LoginRequestDTO;
import com.shopykart.auth.dto.LoginResponseDTO;
import com.shopykart.user.dto.UserRegisterRequest;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    void register(UserRegisterRequest request);
    LoginResponseDTO login(LoginRequestDTO request);
}
