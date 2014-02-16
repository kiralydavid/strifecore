package com.strifecore.core.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class HeroBuilder {
    private String name;
    private AttributeMap attributes;
    private SortedSet<Skill> skills;

    public HeroBuilder() {
        this.attributes = new AttributeMap();
        this.skills = new TreeSet<>();
    }

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder addAttribute(AttributeName type, Attribute attribute) {
        attributes.addAttribute(type, attribute);

        return this;
    }

    public HeroBuilder addSkill(Skill skill) {
        skills.add(skill);

        return this;
    }

    public Hero build() {
        Hero hero = new Hero(name, attributes, skills);
        for(Skill skill : skills) {
            skill.setHero(hero);
        }
        return hero;
    }
}