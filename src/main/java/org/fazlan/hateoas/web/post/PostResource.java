package org.fazlan.hateoas.web.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PostResource extends ResourceSupport {

    private String content;
    private Post post;

    @JsonCreator
    public PostResource(@JsonProperty("content") String content) {
        this.content = content;
    }

    public PostResource(Post post) {
        this.post = post;
        add(linkTo(methodOn(PostController.class).getPost(post.getPid())).withSelfRel());
    }

    public String getContent() {
        if (content != null)
            return content;
        return post.getContent();
    }

    public Link getComments() {
        return  linkTo(methodOn(PostController.class).getComments(post.getPid())).withRel("comments");
    }

}
