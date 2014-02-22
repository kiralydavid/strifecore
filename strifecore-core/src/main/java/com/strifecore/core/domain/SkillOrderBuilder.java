package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class SkillOrderBuilder {

    protected String title;
    protected String comment;
    protected List<SkillSlot> elements;
    protected Integer position;

    public SkillOrderBuilder() {
        this.elements = new LinkedList<>();
    }

    public SkillOrderBuilder addElement(SkillSlot element) {
        this.elements.add(element);
        return this;
    }

    public SkillOrderBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SkillOrderBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public SkillOrderBuilder setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public SkillOrder build() {
        return new SkillOrder(title, comment, elements, position);
    }
}
