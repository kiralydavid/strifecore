package com.strifecore.core.domain;

import java.util.Date;
import java.util.List;

public class Guide {

    private String title;

    private User author;

    private Date created;

    private Date lastEdited;

    private Pet pet;

    private Hero hero;

    private List<ItemOrder> itemOrders;

    private List<SkillOrder> skillOrders;

    private List<Paragraph> paragraphs;

    private List<Comment> comments;

    private Integer upvotes;

    private Integer downvotes;

    private Integer views;

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
                 Integer views) {
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
    }


}
