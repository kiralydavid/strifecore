package com.strifecore.core.service;

import com.strifecore.core.security.AuthenticationDto;

public interface AuthenticationService {

    public AuthenticationDto authenticate(String username, String password);

}
