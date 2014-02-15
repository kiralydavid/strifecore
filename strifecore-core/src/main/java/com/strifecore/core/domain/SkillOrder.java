package com.strifecore.core.domain;

import java.util.Collections;
import java.util.List;

public class SkillOrder extends EntityOrder<SkillSlot> {
    public SkillOrder(String title, String comment, List<SkillSlot> elements) {
        super(title, comment, elements);

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
}
