package com.strifecore.core.repository;

import com.strifecore.core.BaseTest;
import com.strifecore.core.domain.*;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HeroRepositoryTest extends BaseTest {

    private Hero hero;

    @Autowired
    private HeroRepository repository;

    @Before
    public void setUp() throws Exception {
        hero = new HeroBuilder()
                .setName("Caprice")

                .addAttribute(AttributeName.ATTACK, new ScalingAttribute(54D, 2.48, 15))
                .addAttribute(AttributeName.ATTACK_SPEED, new StaticAttribute(0.9))
                .addAttribute(AttributeName.MANA, new ScalingAttribute(240D, 10D, 15))
                .addAttribute(AttributeName.MANA_REGEN, new StaticAttribute(3.7))
                .addAttribute(AttributeName.HEALTH, new ScalingAttribute(603D, 41D, 15))
                .addAttribute(AttributeName.HEALTH_REGEN, new ScalingAttribute(1.8, 0.12, 15))
                .addAttribute(AttributeName.MOVEMENT_SPEED, new StaticAttribute(330D))
                .addAttribute(AttributeName.ARMOR, new ScalingAttribute(10D, 1.5, 15))
                .addAttribute(AttributeName.MAGIC_ARMOR, new ScalingAttribute(10D, 1.5, 15))
                .addAttribute(AttributeName.ATTACK_RANGE, new StaticAttribute(500D))

                .addSkill(new SkillBuilder()
                        .setName("Fire Lager")
                        .setDescription("Target direction to deal 70/90/110/130 Magic damage in a line. Line explodes after 0.9 seconds, dealing half damage.")
                        .setSlot(SkillSlot.FIRST)
                        .addAttribute(AttributeName.MANA_COST, new ScalingAttribute(70D, 10D, 4))
                        .addAttribute(AttributeName.COOLDOWN, new StaticAttribute(9D))
                        .addAttribute(AttributeName.RANGE, new StaticAttribute(650D))
                        .addAttribute(AttributeName.RADIUS, new StaticAttribute(180D))
                        .build()
                )
                .addSkill(new SkillBuilder()
                        .setName("Anchors Aweigh")
                        .setDescription("Target location to deal 70/95/120/145 Magic damage and stun enemies for 0.7/0.9/1.1/1.3 seconds.")
                        .setSlot(SkillSlot.SECOND)
                        .addAttribute(AttributeName.MANA_COST, new ScalingAttribute(70D, 10D, 4))
                        .addAttribute(AttributeName.COOLDOWN, new StaticAttribute(11D))
                        .addAttribute(AttributeName.RANGE, new StaticAttribute(550D))
                        .addAttribute(AttributeName.RADIUS, new StaticAttribute(200D))
                        .build()
                )
                .addSkill(new SkillBuilder()
                        .setName("Volatility")
                        .setDescription("Every third source of damage you apply to a target combusts, dealing 30/45/60/75 bonus Magic damage.")
                        .setSlot(SkillSlot.THIRD)
                        .build()
                )
                .addSkill(new SkillBuilder()
                        .setName("Quick Draw")
                        .setDescription("Target location to leap there, dealing 90/120/150 Magic damage to the nearest enemy. Prioritizes heroes.")
                        .setSlot(SkillSlot.ULTI)
                        .addAttribute(AttributeName.MANA_COST, new ScalingAttribute(90D, 10D, 3))
                        .addAttribute(AttributeName.COOLDOWN, new StaticAttribute(40D))
                        .addAttribute(AttributeName.RANGE, new StaticAttribute(600D))
                        .build()
                )
                .build();
    }

    @Test
    public void testCreate() throws Exception {
        Integer id = repository.create(hero);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

        Hero heroFromDb = repository.read(id);
        Hibernate.initialize(heroFromDb);
    }
}
