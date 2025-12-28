package com.shopykart.user.service.impl;


import com.shopykart.user.dto.UserRegisterRequest;
import com.shopykart.user.entity.User;
import com.shopykart.user.enums.RegistrationType;
import com.shopykart.user.repository.UserRepository;
import com.shopykart.user.service.UserService;
import com.shopykart.user.service.strategy.UserRegistrationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final List<UserRegistrationStrategy> strategies;
    @Autowired
    private  final UserRepository userRepository;
    public UserServiceImpl(List<UserRegistrationStrategy> strategies, UserRepository userRepository) {
        this.strategies = strategies;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(RegistrationType type) {
        UserRegistrationStrategy strategy = strategies.stream()
                .filter(s -> s.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid registration type"));
        strategy.register();

    }
    @Override
    public void registerUser(UserRegisterRequest request) {

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword() // encryption comes next
        );
                 userRepository.save(user);
    }
}
