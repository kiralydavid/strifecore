package com.strifecore.core.domain;

import com.strifecore.core.BaseTest;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ItemOrderTest extends BaseTest {

    @Test
    public void testPersistingItemOrder() throws Exception {

        Component mainComponent = new ComponentBuilder()
                .setName("Empowered Bracer")
                .setDevName("gauntlet")
                .setCraftValue(2)
                .setDescription("Description")
                .setPrice(550)
                .setType(ItemType.POWER)
                .addBonus(new Bonus(BonusType.POWER, 7D))
                .addBonus(new Bonus(BonusType.HEALTH, 110D))
                .setImage("gauntlet.png")
                .build();

        Item item = new ItemBuilder()
                .setComponent(mainComponent)
                .build();

        sessionFactory.getCurrentSession().save(mainComponent);
        sessionFactory.getCurrentSession().save(item);
        sessionFactory.getCurrentSession().flush();

        ItemOrder itemOrder = new ItemOrderBuilder()
                .setTitle("TestItemOrder")
                .setComment("Some comment")
                .addElement(item)
                .addElement(item)
                .addElement(item)
                .setPosition(1)
                .build();


        sessionFactory.getCurrentSession().save(itemOrder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItemOrderCreation() throws Exception {
        ItemOrder itemOrder = (ItemOrder) new ItemOrderBuilder()
                .setTitle("Invalid Itemorder")
                .setComment("None")
                .build();

        fail();
    }
}
