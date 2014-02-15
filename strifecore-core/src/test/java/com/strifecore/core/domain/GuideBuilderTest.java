package com.strifecore.core.domain;

import org.junit.Test;

import java.util.Date;

public class GuideBuilderTest {
    @Test
    public void testBuild() throws Exception {

        User author = new User();
        author.setName("TestUser");

        Skill skill = new SkillBuilder()
                .setName("Fire Lager")
                .setDescription("Target direction to deal 70/90/110/130 Magic damage in a line. Line explodes after 0.9 seconds, dealing half damage.")
                .addAttribute(SkillAttributes.MANA_COST, new FixedStepAttribute().addValue(70D).addValue(80D).addValue(90D).addValue(100D))
                .addAttribute(SkillAttributes.COOLDOWN, new StaticAttribute(9D))
                .addAttribute(SkillAttributes.RANGE, new StaticAttribute(650D))
                .addAttribute(SkillAttributes.RADIUS, new StaticAttribute(180D))
                .setSlot(SkillSlot.FIRST)
                .build();

        Hero hero = new HeroBuilder()
                .setName("Caprice")
                .addAttribute(HeroAttributes.ATTACK, new ScalingAttribute(54D, 2.485, 15))
                .addAttribute(HeroAttributes.ATTACK_SPEED, new StaticAttribute(0.9))
                .addSkill(skill)
                .build();

        Pet pet = new PetBuilder()
                .setName("Razer")
                .addSkill(new SkillBuilder()
                    .setName("Into the Shadows")
                    .addAttribute(SkillAttributes.COOLDOWN, new FixedStepAttribute().addValue(270D).addValue(180D).addValue(180D))
                    .build()
                )
                .build();

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

        Item item = new ItemBuilder()
                .setCrafter(author)
                .setComponent(mainComponent)
                .addSubcomponent(firstComponent)
                .addSubcomponent(secondComponent)
                .build();


        ItemOrder itemOrder = (ItemOrder) new ItemOrderBuilder()
                .setTitle("First Itemorder")
                .setComment("Some comment")
                .addElement(item)
                .addElement(item)
                .build();

        SkillOrder skillOrder = (SkillOrder) new SkillOrderBuilder()
                .setTitle("Main skillorder")
                .setComment("Some other comment")
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.FIRST)
                .addElement(SkillSlot.SECOND)
                .addElement(SkillSlot.SECOND)
                .addElement(SkillSlot.SECOND)
                .addElement(SkillSlot.SECOND)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.THIRD)
                .addElement(SkillSlot.ULTI)
                .addElement(SkillSlot.ULTI)
                .addElement(SkillSlot.ULTI)
                .build();


        Guide guide = new GuideBuilder()
                .setTitle("Test Guide")
                .setAuthor(author)
                .setCreated(new Date())
                .setLastEdited(new Date())
                .setHero(hero)
                .setPet(pet)
                .addItemOrder(itemOrder)
                .addSkillOrder(skillOrder)
                .addParagraph(new Paragraph("Paragraph", "Content"))
                .addComment(new Comment(author, new Date(), "Comment"))
                .setUpvotes(10)
                .setDownvotes(9)
                .setViews(210)
                .build();
    }
}
