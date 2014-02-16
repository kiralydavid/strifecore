package com.strifecore.core.service;

import com.strifecore.core.domain.User;
import com.strifecore.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;

    @Override
    @Transactional
    public User getByName(String name) {
        UserDetails userDetails = userDao.getByName(name);

        if(userDetails == null) {
            throw new UsernameNotFoundException("Username " + name + " not found!");
        }

        return userDao.getByName(name);
    }
}
