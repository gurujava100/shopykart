package com.shopykart.user.service.strategy;

import com.shopykart.user.enums.RegistrationType;
import org.springframework.stereotype.Component;

@Component
public class MobileRegistrationStrategy implements UserRegistrationStrategy {
    @Override
    public void register() {
        System.out.println("Registering user via MOBILE");
    }

    @Override
    public RegistrationType getType() {
        return RegistrationType.MOBILE;
    }
}
