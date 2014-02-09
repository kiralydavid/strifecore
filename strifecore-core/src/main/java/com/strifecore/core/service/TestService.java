package com.strifecore.core.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface TestService {
    public String hello(String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String securedHello(String name);
}
