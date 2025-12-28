package com.shopykart.user.service.strategy;

import com.shopykart.user.enums.RegistrationType;
import org.springframework.stereotype.Component;

@Component
public class EmailRegistrationStrategy implements UserRegistrationStrategy{

    @Override
    public void register() {
        System.out.println("Register via Email");
    }

    @Override
    public RegistrationType getType() {
        return RegistrationType.EMAIL;
    }
}
