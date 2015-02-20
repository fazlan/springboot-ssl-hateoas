package org.fazlan.hateoas.web.comment;

public class Comment {

    private final Integer id;
    private final String text;

    public Comment(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
