package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class ItemBuilder {
    private Component component;
    private List<Component> subcomponents;

    public ItemBuilder() {
        this.subcomponents = new LinkedList<>();
    }

    public ItemBuilder setComponent(Component component) {
        this.component = component;
        return this;
    }

    public ItemBuilder addSubcomponent(Component subcomponent) {
        this.subcomponents.add(subcomponent);
        return this;
    }

    public Item build() {
        return new Item(component, subcomponents);
    }
}