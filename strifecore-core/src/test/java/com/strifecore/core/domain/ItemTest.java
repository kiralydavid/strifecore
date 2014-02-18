package com.strifecore.core.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;


public class ItemTest {

    private Item item;

    @Before
    public void setUp() throws Exception {

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

        Component firstComponent = new ComponentBuilder()
                .setName("Power Shard")
                .setDevName("power_1")
                .setCraftValue(1)
                .setDescription("Description")
                .setPrice(450)
                .setType(ItemType.COMPONENT)
                .addBonus(new Bonus(BonusType.POWER, 6D))
                .setImage("power_1.png")
                .build();

        Component secondComponent = new ComponentBuilder()
                .setName("Health Shard")
                .setDevName("health_1")
                .setCraftValue(1)
                .setDescription("Description")
                .setPrice(450)
                .setType(ItemType.COMPONENT)
                .addBonus(new Bonus(BonusType.HEALTH, 85D))
                .setImage("health_1.png")
                .build();


        item = new ItemBuilder()
                .setComponent(mainComponent)
                .addSubcomponent(firstComponent)
                .addSubcomponent(secondComponent)
                .build();
    }

    @Test
    public void testGetBonuses() throws Exception {

        List<Bonus> actualBonuses = item.getBonuses();

        assertThat(actualBonuses, containsInAnyOrder(
                new Bonus(BonusType.HEALTH, 195D),
                new Bonus(BonusType.POWER, 13D)
        ));
    }
}
