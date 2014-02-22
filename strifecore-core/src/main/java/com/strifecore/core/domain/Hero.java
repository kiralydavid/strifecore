package com.strifecore.core.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.util.List;
import java.util.SortedSet;

@Entity
@DiscriminatorValue("HERO")
public class Hero extends Character {

    public static final Integer MAX_LEVEL = 15;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "attribute_map")
    @JsonUnwrapped
    private AttributeMap attributes;

    @OneToMany
    private List<Guide> guides;

    protected Hero() {}

    public Hero(String name, SortedSet<Skill> skills, AttributeMap attributes) {
        super(name, skills);
        this.attributes = attributes;
    }

    public AttributeMap getAttributes() {
        return attributes;
    }
}
