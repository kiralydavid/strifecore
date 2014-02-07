package com.strifecore.core.service;

import com.strifecore.core.BaseTest;
import com.strifecore.core.service.impl.TestServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestServiceTest extends BaseTest {

    private TestService service;

    @Before
    public void setUp() throws Exception {
        service = new TestServiceImpl();
    }

    @Test
    public void testHello() throws Exception {
        Assert.assertEquals("Hello StrifeCore!", service.hello("StrifeCore"));
    }
}
