package com.strifecore.core.service;

import com.strifecore.core.config.RootContext;
import com.strifecore.core.service.impl.TestServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootContext.class)
public class TestServiceTest {

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
