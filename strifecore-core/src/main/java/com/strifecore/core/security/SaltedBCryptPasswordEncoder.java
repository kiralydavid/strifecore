package com.strifecore.core.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SaltedBCryptPasswordEncoder extends BCryptPasswordEncoder {

    private String salt;

    public SaltedBCryptPasswordEncoder(String salt) {
        this.salt = salt;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword + salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return super.matches(rawPassword + salt, encodedPassword);
    }
}
