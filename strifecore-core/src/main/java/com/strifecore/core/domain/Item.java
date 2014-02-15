package com.strifecore.core.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Item {

    private Component component;

    private List<Component> subcomponents;

    private User crafter;

    public Item(Component component, List<Component> subcomponents, User crafter) {
        this.component = component;
        this.subcomponents = subcomponents;
        this.crafter = crafter;
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
