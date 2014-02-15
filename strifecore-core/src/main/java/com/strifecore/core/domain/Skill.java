package com.strifecore.core.domain;

public class Skill implements Comparable<Skill> {

    private String name;

    private String description;

    private AttributeMap<SkillAttributes> attributes;

    private SkillSlot slot;

    protected Skill(String name, String description, AttributeMap<SkillAttributes> attributes, SkillSlot slot) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
        this.slot = slot;
    }

    @Override
    public int compareTo(Skill other) {
        return slot.value() - other.slot.value();
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}
