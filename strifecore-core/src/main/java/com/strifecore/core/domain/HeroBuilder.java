package com.strifecore.core.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class HeroBuilder {
    private String name;
    private AttributeMap<HeroAttributes> attributes;
    private SortedSet<Skill> skills;

    public HeroBuilder() {
        this.attributes = new AttributeMap<>();
        this.skills = new TreeSet<>();
    }

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HeroBuilder addAttribute(HeroAttributes type, Attribute attribute) {
        attributes.addAttribute(type, attribute);

        return this;
    }

    public HeroBuilder addSkill(Skill skill) {
        skills.add(skill);

        return this;
    }

    public Hero build() {
        return new Hero(name, attributes, skills);
    }
}