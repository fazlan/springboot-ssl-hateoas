package org.fazlan.hateoas.web.post;

import org.fazlan.hateoas.web.comment.Comment;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private final Integer pid;
    private final String content;
    private final List<Comment> comments;

    public Post(Integer pid, String content) {
        this.pid = pid;
        this.content = content;
        comments = new ArrayList<>();
    }

    public Integer getPid() {
        return pid;
    }

    public String getContent() {
        return content;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}
