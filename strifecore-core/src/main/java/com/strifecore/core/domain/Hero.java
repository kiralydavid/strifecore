package com.strifecore.core.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.SortedSet;

@Entity
@Table(name = "hero")
public class Hero {

    public static final Integer MAX_LEVEL = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "attribute_map")
    private AttributeMap attributes;

    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "hero")
    @Sort(type = SortType.NATURAL)
    private SortedSet<Skill> skills;

    protected Hero() {}

    public Hero(String name, AttributeMap attributes, SortedSet<Skill> skills) {
        this.name = name;
        this.attributes = attributes;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name=" + name +
                '}';
    }
}
