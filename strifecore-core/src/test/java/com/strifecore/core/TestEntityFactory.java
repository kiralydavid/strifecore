package com.strifecore.core;

import com.strifecore.core.domain.User;

public class TestEntityFactory {

    public static User getUser(){
        User user = new User();
        user.setName("TestUser");
        user.setEmail("test@test.com");
        user.setPassword("1234567890");
        user.setActive(true);
        user.setAdmin(false);

        return user;
    }

}
