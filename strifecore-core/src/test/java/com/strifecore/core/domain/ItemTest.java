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
                .setName(new ComponentName("Empowered Bracer", "gauntlet"))
                .setCraftValue(2)
                .setDescription("Description")
                .setPrice(550)
                .setType(ItemType.POWER)
                .addBonus(new Bonus(BonusType.POWER, 7D))
                .addBonus(new Bonus(BonusType.HEALTH, 110D))
                .build();

        Component firstComponent = new ComponentBuilder()
                .setName(new ComponentName("Power Shard", "power_1"))
                .setCraftValue(1)
                .setDescription("Description")
                .setPrice(450)
                .setType(ItemType.COMPONENT)
                .addBonus(new Bonus(BonusType.POWER, 6D))
                .build();

        Component secondComponent = new ComponentBuilder()
                .setName(new ComponentName("Health Shard", "health_1"))
                .setCraftValue(1)
                .setDescription("Description")
                .setPrice(450)
                .setType(ItemType.COMPONENT)
                .addBonus(new Bonus(BonusType.HEALTH, 85D))
                .build();


        item = new ItemBuilder()
                .setComponent(mainComponent)
                .addSubcomponent(firstComponent)
                .addSubcomponent(secondComponent)
                .setCrafter(new User())
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
