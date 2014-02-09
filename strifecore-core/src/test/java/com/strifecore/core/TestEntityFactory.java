package com.strifecore.core;

import com.strifecore.core.domain.User;

public class TestEntityFactory {

    public static User getUser(){
        User user = new User();
        user.setName("TestUser");
        user.setEmail("test@test.com");
        user.setPassword("$2a$10$2.6UN5FY3RZzwsEClsLHf.tkmBz/Iznsbx8sOk1FQz11XijDtt4la");
        user.setActive(true);
        user.setAdmin(false);

        return user;
    }

}
