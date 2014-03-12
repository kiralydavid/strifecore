package com.strifecore.core.service.impl;

import com.strifecore.core.domain.User;
import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.security.TokenUtils;
import com.strifecore.core.service.AuthenticationService;
import com.strifecore.core.util.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Clock clock;

    private Long tokenExpirationTime;

    @Autowired
    public AuthenticationServiceImpl(Long tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    @Override
    @Transactional
    public AuthenticationDto authenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        String token = tokenUtils.createToken(userDetails, clock.getTimeInMillis() + tokenExpirationTime);

        User user = (User)userDetails;

        return new AuthenticationDto(username, token, user.isAdmin());
    }
}
