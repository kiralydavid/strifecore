package com.strifecore.core.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class PetBuilder {
    private String name;
    private SortedSet<Skill> skills;

    public PetBuilder() {
        this.skills = new TreeSet<>();
    }

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder addSkill(Skill skill) {
        this.skills.add(skill);
        return this;
    }

    public Pet build() {
        Pet pet = new Pet(name, skills);
        for(Skill skill : skills) {
            skill.setCharacter(pet);
        }
        return pet;
    }
}