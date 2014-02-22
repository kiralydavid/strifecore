package com.strifecore.core.domain;

import com.strifecore.core.BaseTest;
import org.junit.Test;

import static org.junit.Assert.fail;

public class SkillOrderTest extends BaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidElementSkillOrderCreation() throws Exception {
        SkillOrder skillOrder = (SkillOrder) new SkillOrderBuilder()
                .setTitle("Main skillorder")
                .setComment("Some other comment")
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .build();

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDistributionSkillOrderCreation() throws Exception {
        SkillOrder skillOrder = (SkillOrder) new SkillOrderBuilder()
                .setTitle("Main skillorder")
                .setComment("Some other comment")
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.ULTI)
                .addElement(SkillSlot.ULTI)
                .addElement(SkillSlot.ULTI)
                .build();

        fail();
    }
}
