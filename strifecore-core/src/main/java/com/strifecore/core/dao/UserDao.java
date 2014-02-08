package com.strifecore.core.dao;

import com.strifecore.core.domain.User;

public interface UserDao {

    public User read(Integer id);

    public Integer create(User user);

    public User getByName(String name);

}
