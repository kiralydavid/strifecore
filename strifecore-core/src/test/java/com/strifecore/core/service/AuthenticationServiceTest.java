package com.strifecore.core.service;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import com.strifecore.core.security.AuthenticationDto;
import com.strifecore.core.security.TokenUtils;
import com.strifecore.core.service.impl.AuthenticationServiceImpl;
import com.strifecore.core.util.Clock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest extends BaseTest {

    private User user;

    @Mock
    private TokenUtils tokenUtils;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Clock clock;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private AuthenticationDto mockDto;

    @Before
    public void setUp() throws Exception {
        authenticationService = new AuthenticationServiceImpl(1000L);

        MockitoAnnotations.initMocks(this);

        user = testEntityFactory.getUser();
        user.setPassword("1234567890");
        mockDto = new AuthenticationDto("TestUser", "TestUser:1000:12312312312312", false);

        when(userDetailsService.loadUserByUsername("TestUser")).thenReturn(user);

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), "wrongpassword")))
                .thenThrow(new BadCredentialsException(""));


        when(tokenUtils.createToken(user, 1000L)).thenReturn("TestUser:1000:12312312312312");
    }

    @Test
    public void testAuthenticate() throws Exception {
        AuthenticationDto authenticationDto = authenticationService.authenticate("TestUser", "1234567890");

        assertTrue(new ReflectionEquals(mockDto).matches(authenticationDto));

    }

    @Test(expected = BadCredentialsException.class)
    public void testAuthenticateWithWrongPassword() throws Exception {
        AuthenticationDto authenticationDto = authenticationService.authenticate("TestUser", "wrongpassword");

        fail();
    }
}
