package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class SkillBuilder {
    private String name;
    private String description;
    private AttributeMap attributes;
    private List<Attribute> orangeAttributes;
    private SkillSlot slot;

    public SkillBuilder() {
        this.attributes = new AttributeMap();
        this.orangeAttributes = new LinkedList<>();
    }

    public SkillBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SkillBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public SkillBuilder addAttribute(AttributeName type, Attribute attribute) {
        this.attributes.addAttribute(type, attribute);

        return this;
    }

    public SkillBuilder addOrangeAttribute(Attribute attribute) {
        this.orangeAttributes.add(attribute);

        return this;
    }

    public SkillBuilder setSlot(SkillSlot slot) {
        this.slot = slot;
        return this;
    }

    public Skill build() {
        return new Skill(name, description, attributes, orangeAttributes, slot);
    }
}