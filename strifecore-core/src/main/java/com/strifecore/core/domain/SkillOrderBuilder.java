package com.strifecore.core.domain;

public class SkillOrderBuilder extends EntityOrderBuilder<SkillSlot> {
    @Override
    public SkillOrder build() {
        return new SkillOrder(title, comment, elements);
    }
}
