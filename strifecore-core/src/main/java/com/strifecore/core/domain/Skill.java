package com.strifecore.core.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

@Entity
public class Skill implements Comparable<Skill> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "attribute_map")
    private AttributeMap attributes;

    private SkillSlot slot;

    @ManyToOne
    @JoinColumn(name = "hero")
    private Hero hero;

    protected Skill() {}

    protected Skill(String name, String description, AttributeMap attributes, SkillSlot slot) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
        this.slot = slot;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public int compareTo(Skill other) {
        return slot.value() - other.slot.value();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @JsonUnwrapped
    public AttributeMap getAttributes() {
        return attributes;
    }

    public SkillSlot getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}
