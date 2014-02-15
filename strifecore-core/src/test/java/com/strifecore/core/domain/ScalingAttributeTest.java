package com.strifecore.core.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ScalingAttributeTest {

    private ScalingAttribute attribute;

    @Before
    public void setUp() throws Exception {
        attribute = new ScalingAttribute(10D, 1.5, Hero.MAX_LEVEL);
    }

    @Test
    public void testGetValueOnLevel() throws Exception {
        assertEquals((Double)17.5, attribute.getValueOnLevel(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValueOnInvalidLevel() throws Exception {
        attribute.getValueOnLevel(-1);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetValueOnLevelAboveMax() throws Exception {
        attribute.getValueOnLevel(17);
        fail();
    }
}
