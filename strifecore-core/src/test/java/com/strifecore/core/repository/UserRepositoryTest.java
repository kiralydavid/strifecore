package com.strifecore.core.repository;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserRepositoryTest extends BaseTest {

    private User user;

    @Autowired
    private UserRepository userDao;

    @Before
    public void setUp() throws Exception {
        user = testEntityFactory.getUser();
    }

    @Test
    public void testRead() throws Exception {
        Integer userId = (Integer)sessionFactory.getCurrentSession().save(user);

        User userFromDb = userDao.read(userId);

        assertNotNull(userFromDb);
        assertTrue(new ReflectionEquals(user).matches(userFromDb));
    }

    @Test
    public void testCreate() throws Exception {
        Integer userId = userDao.create(user);

        User userFromDb = (User)sessionFactory.getCurrentSession().get(User.class, userId);

        assertNotNull(userFromDb);
        assertTrue(new ReflectionEquals(user).matches(userFromDb));
    }

    @Test
    public void testGetByName() throws Exception {
        Integer userId = (Integer)sessionFactory.getCurrentSession().save(user);

        User userFromDb = userDao.getByName(user.getName());

        assertTrue(new ReflectionEquals(user).matches(userFromDb));
    }

    @Test
    public void testGetByWrongName() throws Exception {
        Integer userId = (Integer)sessionFactory.getCurrentSession().save(user);

        User userFromDb = userDao.getByName("WrongUsername");

        assertNull(userFromDb);
    }
}
