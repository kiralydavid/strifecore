package com.strifecore.core.security;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import com.strifecore.core.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

public class DaoUserDetailsServiceTest extends BaseTest {

    private User user;

    @Mock
    private UserRepository userDao;

    @InjectMocks
    private DaoUserDetailsService userDetailsService;

    @Before
    public void setUp() throws Exception {

        user = testEntityFactory.getUser();

        MockitoAnnotations.initMocks(this);

        when(userDao.getByName("TestUser")).thenReturn(user);


    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        User userFromService = (User)userDetailsService.loadUserByUsername(user.getName());

        assertTrue(new ReflectionEquals(userFromService).matches(user));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByWrongUsername() throws Exception {
        userDetailsService.loadUserByUsername("WrongUsername");

        fail();
    }
}
