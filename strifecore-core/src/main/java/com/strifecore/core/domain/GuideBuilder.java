package com.strifecore.core.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GuideBuilder {
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
    private Boolean active;
    private Boolean published;

    public GuideBuilder() {
        this.itemOrders = new LinkedList<>();
        this.skillOrders = new LinkedList<>();
        this.paragraphs = new LinkedList<>();
        this.comments = new LinkedList<>();
    }

    public GuideBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public GuideBuilder setAuthor(User author) {
        this.author = author;
        return this;
    }

    public GuideBuilder setCreated(Date created) {
        this.created = created;
        return this;
    }

    public GuideBuilder setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
        return this;
    }

    public GuideBuilder setPet(Pet pet) {
        this.pet = pet;
        return this;
    }

    public GuideBuilder setHero(Hero hero) {
        this.hero = hero;
        return this;
    }

    public GuideBuilder addItemOrder(ItemOrder itemOrder) {
        this.itemOrders.add(itemOrder);

        return this;
    }

    public GuideBuilder addSkillOrder(SkillOrder skillOrder) {
        this.skillOrders.add(skillOrder);

        return this;
    }

    public GuideBuilder addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);

        return this;
    }

    public GuideBuilder addComment(Comment comment) {
        this.comments.add(comment);

        return this;
    }

    public GuideBuilder setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
        return this;
    }

    public GuideBuilder setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
        return this;
    }

    public GuideBuilder setViews(Integer views) {
        this.views = views;
        return this;
    }

    public GuideBuilder setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public GuideBuilder setPublished(Boolean published) {
        this.published = published;
        return this;
    }

    public Guide build() {

        Guide guide = new Guide(title, author, created, lastEdited, pet, hero, itemOrders, skillOrders, paragraphs, comments, upvotes, downvotes, views, active, published);

        for(SkillOrder skillOrder : guide.getSkillOrders()) {
            skillOrder.setGuide(guide);
        }

        for(ItemOrder itemOrder : guide.getItemOrders()) {
            itemOrder.setGuide(guide);
        }

        for(Comment comment : guide.getComments()) {
            comment.setGuide(guide);
        }

        for(Paragraph paragraph : guide.getParagraphs()) {
            paragraph.setGuide(guide);
        }

        return guide;
    }
}