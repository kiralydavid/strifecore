package com.strifecore.core.domain;

import java.util.List;

public class Pet {

    private String name;

    private List<Skill> skills;

    public Pet(String name, List<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }
}
