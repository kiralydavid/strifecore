package com.strifecore.api.controller;

import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String exceptionHandler(){
        return "Bad Credentials";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody AuthenticationDto login(@RequestParam String username, @RequestParam String password) {
        AuthenticationDto authenticationDto = authenticationService.authenticate(username, password);
        return authenticationDto;
    }

}
