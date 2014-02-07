package com.strifecore.core.dao;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDaoTest extends BaseTest {

    private User user;

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("TestUser");
        user.setEmail("test@test.com");
        user.setPassword("1234567890");
        user.setActive(true);
        user.setAdmin(false);
    }

    @Test
    public void testRead() throws Exception {
        Integer userId = (Integer)sessionFactory.getCurrentSession().save(user);

        User userFromDb = userDao.read(userId);

        assertNotNull(userFromDb);
        assertEquals(user.getName(), userFromDb.getName());
        assertEquals(user.getEmail(), userFromDb.getEmail());
        assertEquals(user.getPassword(), userFromDb.getPassword());
        assertEquals(user.isActive(), userFromDb.isActive());
        assertEquals(user.isAdmin(), userFromDb.isAdmin());
    }

    @Test
    public void testCreate() throws Exception {
        Integer userId = userDao.create(user);

        User userFromDb = (User)sessionFactory.getCurrentSession().get(User.class, userId);

        assertNotNull(userFromDb);
        assertEquals(user.getName(), userFromDb.getName());
        assertEquals(user.getEmail(), userFromDb.getEmail());
        assertEquals(user.getPassword(), userFromDb.getPassword());
        assertEquals(user.isActive(), userFromDb.isActive());
        assertEquals(user.isAdmin(), userFromDb.isAdmin());
    }
}
