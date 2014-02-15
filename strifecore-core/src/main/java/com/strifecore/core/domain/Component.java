package com.strifecore.core.domain;

import java.util.List;

public class Component {

    private ComponentName name;
    private Integer craftValue;
    private Integer price;
    private ItemType type;
    private String description;
    private List<Bonus> bonuses;

    public Component(ComponentName name, Integer craftValue, Integer price, ItemType type, String description, List<Bonus> bonuses) {
        this.name = name;
        this.craftValue = craftValue;
        this.price = price;
        this.type = type;
        this.description = description;
        this.bonuses = bonuses;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", craftValue=" + craftValue +
                ", price=" + price +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", bonuses=" + bonuses +
                '}';
    }
}
