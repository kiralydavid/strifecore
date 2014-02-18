package com.strifecore.core.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.List;
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
    @JsonUnwrapped
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

    public Integer getId() {
        return id;
    }

    public AttributeMap getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public SortedSet<Skill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name=" + name +
                '}';
    }
}
