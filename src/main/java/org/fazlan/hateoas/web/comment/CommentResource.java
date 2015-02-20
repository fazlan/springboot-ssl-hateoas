package org.fazlan.hateoas.web.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CommentResource extends ResourceSupport {

    private Comment comment;
    private String text;

    @JsonCreator
    public CommentResource(@JsonProperty("text") String text) {
        this.text = text;
    }

    public CommentResource(Comment comment) {
        this.comment = comment;
    }

    public CommentResource withSefRef() {
        add(linkTo(methodOn(CommentController.class).getComment(comment.getId())).withSelfRel());
        return this;
    }

    public CommentResource withRel(String rel) {
        add(linkTo(methodOn(CommentController.class).getComment(comment.getId())).withRel(rel));
        return this;
    }

    public String getText() {
        if (text != null)
            return text;
        return comment.getText();
    }
}
