package com.strifecore.core.domain;

public enum SkillSlot {
    FIRST(0), SECOND(1), THIRD(2), ULTI(3);


    private Integer value;

    SkillSlot(Integer value){
        this.value = value;
    }

    public int value() {
        return value;
    }
}
