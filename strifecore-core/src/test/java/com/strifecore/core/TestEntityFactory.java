package com.strifecore.core;

import com.strifecore.core.domain.User;
import com.strifecore.core.security.SaltedBCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestEntityFactory {

    @Autowired
    SaltedBCryptPasswordEncoder passwordEncoder;

    public User getUser(){
        User user = new User();
        user.setName("TestUser");
        user.setEmail("test@test.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setActive(true);
        user.setAdmin(false);

        return user;
    }

}
