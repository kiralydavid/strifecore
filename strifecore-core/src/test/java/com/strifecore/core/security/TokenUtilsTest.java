package com.strifecore.core.security;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import com.strifecore.core.util.MockClock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenUtilsTest extends BaseTest {

    private TokenUtils tokenUtils;

    private User user;

    private MockClock clock;

    @Before
    public void setUp() throws Exception {

        clock = new MockClock();
        clock.setTime(1391960630216L);

        tokenUtils = new TokenUtils("s3cr3t", clock);

        user = testEntityFactory.getUser();
        user.setPassword("1234567890");
    }

    @Test
    public void testComputeSignature() throws Exception {

        String signature = tokenUtils.computeSignature(user, 10000L);

        assertEquals("6a064eb7f73e1c82203ded20dc31e7b34e24cc7f0ab7f345b65a5f12a61f7527", signature);
    }

    @Test
    public void testCreateToken() throws Exception {

        String token = tokenUtils.createToken(user, 10000L);

        assertEquals("TestUser:10000:6a064eb7f73e1c82203ded20dc31e7b34e24cc7f0ab7f345b65a5f12a61f7527", token);
    }

    @Test
    public void testValidateToken() throws Exception {

        String token = tokenUtils.createToken(user, 1391960637216L);

        assertTrue(tokenUtils.validateToken(token, user));
    }

    @Test
     public void testValidateExpiredToken() throws Exception {

        clock.setTime(1391981138979L);

        String token = tokenUtils.createToken(user, 1391980337719L);

        assertFalse(tokenUtils.validateToken(token, user));
    }

    @Test
    public void testValidateTokenWithManipulatedExpire() throws Exception {

        clock.setTime(1391960639216L);

        String token = "TestUser:1391960640216:8afb0302ba0266e219e511eff80fa42d585170212991ee2470dd1f4d15b8951b";

        assertFalse(tokenUtils.validateToken(token, user));
    }

    @Test
    public void testValidateTokenWithManipulatedUser() throws Exception {

        clock.setTime(1391960639216L);

        String token = "AnotherUser:1391960639216:8afb0302ba0266e219e511eff80fa42d585170212991ee2470dd1f4d15b8951b";

        assertFalse(tokenUtils.validateToken(token, user));
    }

    @Test
    public void testGetUsernameFromToken() throws Exception {
        String token = "TestUser:1391960639216:8afb0302ba0266e219e511eff80fa42d585170212991ee2470dd1f4d15b8951b";

        assertEquals("TestUser", tokenUtils.getUserNameFromToken(token));
    }
}
