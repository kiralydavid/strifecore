package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class CraftedItemBuilder {
    private Component component;
    private List<Component> subcomponents;
    private User crafter;

    public CraftedItemBuilder() {
        this.subcomponents = new LinkedList<>();
    }

    public CraftedItemBuilder setComponent(Component component) {
        this.component = component;
        return this;
    }

    public CraftedItemBuilder addSubcomponent(Component subcomponent) {
        this.subcomponents.add(subcomponent);
        return this;
    }

    public CraftedItemBuilder setCrafter(User crafter) {
        this.crafter = crafter;
        return this;
    }

    public CraftedItem build() {
        return new CraftedItem(component, subcomponents, crafter);
    }
}
