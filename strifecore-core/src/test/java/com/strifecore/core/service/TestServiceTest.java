package com.strifecore.core.service;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class TestServiceTest extends BaseTest {

    @Autowired
    private TestService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testHello() throws Exception {
        Assert.assertEquals("Hello StrifeCore!", service.hello("StrifeCore"));
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testSecuredHelloWithoutLogin() throws Exception {
        service.securedHello("StrifeCore");

        fail();
    }

    @Test
    public void testSecuredHelloWithLogin() throws Exception {

        User user = testEntityFactory.getUser();
        user.setAdmin(true);

        sessionFactory.getCurrentSession().save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("TestUser", "password"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        assertEquals("SecuredHello StrifeCore!", service.securedHello("StrifeCore"));
    }

    @Test(expected = AccessDeniedException.class)
    public void testSecuredHelloWithInsufficientPermission() throws Exception {

        User user = testEntityFactory.getUser();

        sessionFactory.getCurrentSession().save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("TestUser", "password"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        assertEquals("SecuredHello StrifeCore!", service.securedHello("StrifeCore"));
        fail();
    }

    @Test(expected = BadCredentialsException.class)
    public void testSecuredHelloWithWrongLogin() throws Exception {

        User user = testEntityFactory.getUser();

        sessionFactory.getCurrentSession().save(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("TestUser", "wrongpassword"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        assertEquals("SecuredHello StrifeCore!", service.securedHello("StrifeCore"));
    }
}
