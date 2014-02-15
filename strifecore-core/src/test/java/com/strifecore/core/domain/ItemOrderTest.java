package com.strifecore.core.domain;

import org.junit.Test;

import static org.junit.Assert.fail;

public class ItemOrderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItemOrderCreation() throws Exception {
        ItemOrder itemOrder = (ItemOrder) new ItemOrderBuilder()
                .setTitle("Invalid Itemorder")
                .setComment("None")
                .build();

        fail();
    }
}
