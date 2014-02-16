package com.strifecore.core.repository;

import com.strifecore.core.domain.User;

public interface UserRepository {

    public User read(Integer id);

    public Integer create(User user);

    public User getByName(String name);

}
