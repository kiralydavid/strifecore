package com.strifecore.core.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FixedStepAttributeTest {

    private FixedStepAttribute attribute;

    @Before
    public void setUp() throws Exception {
        attribute = new FixedStepAttribute();
        attribute.addValue(100D);
        attribute.addValue(130D);
        attribute.addValue(150D);
        attribute.addValue(200D);
    }

    @Test
    public void testGetValueOnLevel() throws Exception {
        assertEquals((Double)150D, attribute.getValueOnLevel(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValueOnBelowLevel() throws Exception {
        attribute.getValueOnLevel(-1);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValueOnAboveLevel() throws Exception {
        attribute.getValueOnLevel(5);
        fail();
    }
}
