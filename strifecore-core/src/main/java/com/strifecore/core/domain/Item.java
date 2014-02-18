package com.strifecore.core.domain;

import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("CASE WHEN crafter IS NULL THEN 'item' ELSE 'craftedItem' END")
@DiscriminatorValue("item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "component")
    private Component component;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "item_subcomponent", joinColumns = {
            @JoinColumn(name = "item", nullable = false, updatable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "component", nullable = false, updatable = false)
    })
    private List<Component> subcomponents;

    protected Item(){}

    public Item(Component component, List<Component> subcomponents) {
        this.component = component;
        this.subcomponents = subcomponents;
    }

    public List<Bonus> getBonuses() {
        Map<BonusType, Double> bonusMap = new HashMap<>();

        for(Bonus bonus : component.getBonuses()) {
            if(bonusMap.containsKey(bonus.getType())) {
                bonusMap.put(bonus.getType(), bonusMap.get(bonus.getType()) + bonus.getAmount());
            } else {
                bonusMap.put(bonus.getType(), bonus.getAmount());
            }
        }

        for(Component subcomponent : subcomponents) {
            for(Bonus bonus : subcomponent.getBonuses()) {
                if(bonusMap.containsKey(bonus.getType())) {
                    bonusMap.put(bonus.getType(), bonusMap.get(bonus.getType()) + bonus.getAmount());
                } else {
                    bonusMap.put(bonus.getType(), bonus.getAmount());
                }
            }
        }

        List<Bonus> bonusList = new LinkedList<>();

        for(Map.Entry<BonusType, Double> entry : bonusMap.entrySet()) {
            bonusList.add(new Bonus(entry.getKey(), entry.getValue()));
        }

        return bonusList;
    }
}
