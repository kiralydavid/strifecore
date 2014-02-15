package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class PetBuilder {
    private String name;
    private List<Skill> skills;

    public PetBuilder() {
        this.skills = new LinkedList<>();
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
        return new Pet(name, skills);
    }
}