package com.strifecore.core.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.SortedSet;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "character", fetch = FetchType.EAGER)
    @Sort(type = SortType.NATURAL)
    private SortedSet<Skill> skills;

    protected Character() {}

    public Character(String name, SortedSet<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SortedSet<Skill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name=" + name +
                '}';
    }
}
