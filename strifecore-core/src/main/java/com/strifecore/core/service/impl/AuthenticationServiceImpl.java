package com.strifecore.core.service.impl;

import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.security.TokenUtils;
import com.strifecore.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Calendar calendar;

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

        String token = tokenUtils.createToken(userDetails, calendar.getTimeInMillis() + tokenExpirationTime);

        return new AuthenticationDto(username, token);
    }
}
