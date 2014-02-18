package com.strifecore.core.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "dev_name")
    private String devName;

    @Column(name = "craft_value")
    private Integer craftValue;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private ItemType type;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "component")
    private List<Bonus> bonuses;

    private String image;

    protected Component(){}

    public Component(String name, String devName, Integer craftValue, Integer price, ItemType type, String description, List<Bonus> bonuses, String image) {
        this.name = name;
        this.devName = devName;
        this.craftValue = craftValue;
        this.price = price;
        this.type = type;
        this.description = description;
        this.bonuses = bonuses;
        this.image = image;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", devName='" + devName + '\'' +
                ", craftValue=" + craftValue +
                ", price=" + price +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", bonuses=" + bonuses +
                '}';
    }
}
