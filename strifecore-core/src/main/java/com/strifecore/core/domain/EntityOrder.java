package com.strifecore.core.domain;

import java.util.List;

public abstract class EntityOrder<T> {

    protected String title;

    protected String comment;

    protected List<T> elements;

    public EntityOrder(String title, String comment, List<T> elements) {
        this.title = title;
        this.comment = comment;
        this.elements = elements;
    }
}
