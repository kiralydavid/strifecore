package com.strifecore.core.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private Date created;

    @Column(name = "last_edited")
    private Date lastEdited;

    @OneToOne
    @JoinColumn(name = "pet")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "hero")
    private Hero hero;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrder> itemOrders;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillOrder> skillOrders;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paragraph> paragraphs;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    private Integer upvotes;

    private Integer downvotes;

    private Integer views;

    private Boolean active;

    private Boolean published;

    protected Guide(){}

    public Guide(String title,
                 User author,
                 Date created,
                 Date lastEdited,
                 Pet pet,
                 Hero hero,
                 List<ItemOrder> itemOrders,
                 List<SkillOrder> skillOrders,
                 List<Paragraph> paragraphs,
                 List<Comment> comments,
                 Integer upvotes,
                 Integer downvotes,
                 Integer views,
                 Boolean active,
                 Boolean published) {
        this.title = title;
        this.author = author;
        this.created = created;
        this.lastEdited = lastEdited;
        this.pet = pet;
        this.hero = hero;
        this.itemOrders = itemOrders;
        this.skillOrders = skillOrders;
        this.paragraphs = paragraphs;
        this.comments = comments;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.views = views;
        this.active = active;
        this.published = published;
    }

    public List<SkillOrder> getSkillOrders() {
        return skillOrders;
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }
}
