package com.strifecore.core.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "skill_orange_attribute",
        joinColumns = @JoinColumn(name = "skill"),
        inverseJoinColumns = @JoinColumn(name = "attribute")
    )
    @OrderColumn(name = "position")
    private List<Attribute> orangeAttributes;

    private SkillSlot slot;

    @ManyToOne
    @JoinColumn(name = "character")
    private Character character;

    protected Skill() {}

    protected Skill(String name, String description, AttributeMap attributes, List<Attribute> orangeAttributes, SkillSlot slot) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
        this.orangeAttributes = orangeAttributes;
        this.slot = slot;
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

    public List<Attribute> getOrangeAttributes() {
        return orangeAttributes;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}
