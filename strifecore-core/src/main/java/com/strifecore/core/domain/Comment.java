package com.strifecore.core.domain;

import java.util.Date;

public class Comment {

    private User author;

    private Date created;

    private String content;

    public Comment(User author, Date created, String content) {
        this.author = author;
        this.created = created;
        this.content = content;
    }
}
