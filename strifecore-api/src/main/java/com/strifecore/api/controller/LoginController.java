package com.strifecore.api.controller;

import com.strifecore.api.dto.LoginDto;
import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String exceptionHandler(){
        return "Bad Credentials";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public AuthenticationDto login(@RequestBody LoginDto loginDto) {
        AuthenticationDto authenticationDto = authenticationService.authenticate(loginDto.getUsername(), loginDto.getPassword());
        return authenticationDto;
    }
}
