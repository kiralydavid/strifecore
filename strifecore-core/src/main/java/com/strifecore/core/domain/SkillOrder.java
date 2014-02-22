package com.strifecore.core.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class SkillOrder extends EntityOrder {

    @ElementCollection(targetClass = SkillSlot.class)
    @JoinTable(name = "skill_order_element", joinColumns = @JoinColumn(name = "entity_order"))
    @OrderColumn(name = "position")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_slot")
    private List<SkillSlot> elements;

    @ManyToOne
    @JoinColumn(name = "guide")
    protected Guide guide;

    protected SkillOrder(){}

    public SkillOrder(String title, String comment, List<SkillSlot> elements, Integer position) {
        super(title, comment, position);

        this.elements = elements;

        if(elements.size() != 15) {
            throw new IllegalArgumentException("A skill order must have exactly 15 elements!");
        }

        if(Collections.frequency(elements, SkillSlot.FIRST) != 4
                || Collections.frequency(elements, SkillSlot.SECOND) != 4
                || Collections.frequency(elements, SkillSlot.THIRD) != 4
                || Collections.frequency(elements, SkillSlot.ULTI) != 3) {
            throw new IllegalArgumentException("Invalid skill distribution!");
        }
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }
}
