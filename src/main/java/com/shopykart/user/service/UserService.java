package com.shopykart.user.service;

import com.shopykart.user.dto.UserRegisterRequest;
import com.shopykart.user.enums.RegistrationType;

public interface UserService {
       void registerUser(RegistrationType type);
       void registerUser(UserRegisterRequest request);
}
