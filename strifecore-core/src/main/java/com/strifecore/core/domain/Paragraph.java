package com.strifecore.core.domain;

import javax.persistence.*;

@Entity
public class Paragraph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "guide")
    private Guide guide;

    protected Paragraph(){}

    public Paragraph(String title, String content, Integer position) {
        this.title = title;
        this.content = content;
        this.position = position;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }
}
