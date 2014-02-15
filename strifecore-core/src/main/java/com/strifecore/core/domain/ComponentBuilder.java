package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class ComponentBuilder {
    private ComponentName name;
    private Integer craftValue;
    private Integer price;
    private ItemType type;
    private String description;
    private List<Bonus> bonuses;

    public ComponentBuilder() {
        this.bonuses = new LinkedList<>();
    }

    public ComponentBuilder setName(ComponentName name) {
        this.name = name;
        return this;
    }

    public ComponentBuilder setCraftValue(Integer craftValue) {
        this.craftValue = craftValue;
        return this;
    }

    public ComponentBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public ComponentBuilder setType(ItemType type) {
        this.type = type;
        return this;
    }

    public ComponentBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ComponentBuilder addBonus(Bonus bonus) {
        this.bonuses.add(bonus);

        return this;
    }

    public Component build() {
        return new Component(name, craftValue, price, type, description, bonuses);
    }
}