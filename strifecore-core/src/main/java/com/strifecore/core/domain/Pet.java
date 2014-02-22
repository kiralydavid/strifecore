package com.strifecore.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.SortedSet;

@Entity
@DiscriminatorValue("PET")
public class Pet extends Character {
    public static final Integer MAX_LEVEL = 9;

    protected Pet(){}

    public Pet(String name, SortedSet<Skill> skills) {
        super(name, skills);
    }
}
