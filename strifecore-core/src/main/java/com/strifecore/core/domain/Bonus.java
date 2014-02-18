package com.strifecore.core.domain;

import javax.persistence.*;

@Entity
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private BonusType type;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "component")
    private Component component;

    protected Bonus(){}

    public Bonus(BonusType type, Double amount) {
        this.type = type;
        this.amount = amount;
    }

    public BonusType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bonus)) return false;

        Bonus bonus = (Bonus) o;

        if (!amount.equals(bonus.amount)) return false;
        if (type != bonus.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }
}
