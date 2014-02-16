package com.strifecore.core.security;

import com.strifecore.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDetails userDetails = userDao.getByName(s);

        if(userDetails == null) {
            throw new UsernameNotFoundException("Username " + s + " not found!");
        }

        return userDao.getByName(s);
    }
}
