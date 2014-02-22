package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class ItemOrderBuilder {

    protected String title;
    protected String comment;
    protected List<Item> elements;
    protected Integer position;
    protected Guide guide;

    public ItemOrderBuilder() {
        this.elements = new LinkedList<>();
    }

    public ItemOrderBuilder addElement(Item element) {
        this.elements.add(element);
        return this;
    }

    public ItemOrderBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemOrderBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ItemOrderBuilder setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public ItemOrderBuilder setGuide(Guide guide) {
        this.guide = guide;
        return this;
    }

    public ItemOrder build() {
        return new ItemOrder(title, comment, elements, position);
    }
}
