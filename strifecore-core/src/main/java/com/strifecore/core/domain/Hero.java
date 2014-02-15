package com.strifecore.core.domain;

import java.util.SortedSet;

public class Hero {

    public static final Integer MAX_LEVEL = 15;

    private String name;

    private AttributeMap<HeroAttributes> attributes;

    private SortedSet<Skill> skills;

    public Hero(String name, AttributeMap<HeroAttributes> attributes, SortedSet<Skill> skills) {
        this.name = name;
        this.attributes = attributes;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name=" + name +
                '}';
    }
}
