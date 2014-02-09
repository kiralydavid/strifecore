package com.strifecore.core.security;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenUtilsTest extends BaseTest {

    private TokenUtils tokenUtils;

    @Before
    public void setUp() throws Exception {
        tokenUtils = new TokenUtils("s3cr3t");
    }

    @Test
    public void testComputeSignature() throws Exception {
        User user = testEntityFactory.getUser();
        user.setPassword("1234567890");

        String signature = tokenUtils.computeSignature(user, 10000L);

        assertEquals("6a064eb7f73e1c82203ded20dc31e7b34e24cc7f0ab7f345b65a5f12a61f7527", signature);
    }

    @Test
    public void testCreateToken() throws Exception {
        User user = testEntityFactory.getUser();
        user.setPassword("1234567890");

        String token = tokenUtils.createToken(user, 10000L);

        assertEquals("TestUser:10000:6a064eb7f73e1c82203ded20dc31e7b34e24cc7f0ab7f345b65a5f12a61f7527", token);
    }
}
