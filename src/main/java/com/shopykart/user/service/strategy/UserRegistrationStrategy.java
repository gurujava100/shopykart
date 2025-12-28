package com.shopykart.user.service.strategy;

import com.shopykart.user.enums.RegistrationType;

public interface UserRegistrationStrategy {
    void register();

    RegistrationType getType();
}
