package org.fazlan.hateoas.repo;

import org.springframework.stereotype.Repository;
import org.fazlan.hateoas.web.comment.Comment;
import org.fazlan.hateoas.web.comment.CommentResource;

@Repository
public class CommentRepo extends AbstractRepo<CommentResource, Comment> {

    @Override
    protected Comment toEntity(CommentResource resource) {
        return new Comment(COUNTER.get(), resource.getText());
    }
}