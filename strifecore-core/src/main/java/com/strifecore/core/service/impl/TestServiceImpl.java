package com.strifecore.core.service.impl;

import com.strifecore.core.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
