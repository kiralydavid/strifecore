package com.strifecore.core.domain;

public class SkillBuilder {
    private String name;
    private String description;
    private AttributeMap attributes;
    private SkillSlot slot;

    public SkillBuilder() {
        this.attributes = new AttributeMap();
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

    public SkillBuilder setSlot(SkillSlot slot) {
        this.slot = slot;
        return this;
    }

    public Skill build() {
        return new Skill(name, description, attributes, slot);
    }
}