package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public abstract class EntityOrderBuilder<T> {
    protected String title;
    protected String comment;
    protected List<T> elements;

    public EntityOrderBuilder() {
        this.elements = new LinkedList<>();
    }

    public EntityOrderBuilder<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    public EntityOrderBuilder<T> setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public EntityOrderBuilder<T> addElement(T element) {
        this.elements.add(element);
        return this;
    }

    public abstract EntityOrder<T> build();
}