package com.strifecore.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private Date created;

    private String content;

    @ManyToOne
    @JoinColumn(name = "guide")
    private Guide guide;

    protected Comment(){}

    public Comment(User author, Date created, String content) {
        this.author = author;
        this.created = created;
        this.content = content;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }
}
