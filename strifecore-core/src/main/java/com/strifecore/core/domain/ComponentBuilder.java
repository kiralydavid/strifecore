package com.strifecore.core.domain;

import java.util.LinkedList;
import java.util.List;

public class ComponentBuilder {
    private String name;
    private String devName;
    private Integer craftValue;
    private Integer price;
    private ItemType type;
    private String description;
    private List<Bonus> bonuses;
    private String image;

    public ComponentBuilder() {
        this.bonuses = new LinkedList<>();
    }

    public ComponentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ComponentBuilder setDevName(String devName) {
        this.devName = devName;
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

    public ComponentBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ComponentBuilder addBonus(Bonus bonus) {
        this.bonuses.add(bonus);

        return this;
    }

    public Component build() {
        Component component = new Component(name, devName, craftValue, price, type, description, bonuses, image);
        for(Bonus bonus : component.getBonuses()) {
            bonus.setComponent(component);
        }
        return component;
    }
}