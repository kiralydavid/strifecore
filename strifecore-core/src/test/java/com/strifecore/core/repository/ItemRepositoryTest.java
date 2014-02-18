package com.strifecore.core.repository;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.*;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemRepositoryTest extends BaseTest {

    private Item item;

    private Component mainComponent;
    private Component firstComponent;
    private Component secondComponent;

    @Autowired
    private ItemRepository repository;

    @Before
    public void setUp() throws Exception {
        mainComponent = new ComponentBuilder()
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

        firstComponent = new ComponentBuilder()
                .setName("Power Shard")
                .setDevName("power_1")
                .setCraftValue(1)
                .setDescription("Description")
                .setPrice(450)
                .setType(ItemType.COMPONENT)
                .addBonus(new Bonus(BonusType.POWER, 6D))
                .setImage("power_1.png")
                .build();

        secondComponent = new ComponentBuilder()
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
    public void testCreate() throws Exception {

        sessionFactory.getCurrentSession().save(mainComponent);
        sessionFactory.getCurrentSession().save(firstComponent);
        sessionFactory.getCurrentSession().save(secondComponent);

        Integer id = repository.create(item);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

        Item itemFromDb = repository.read(id);
        Hibernate.initialize(itemFromDb);
    }
}
