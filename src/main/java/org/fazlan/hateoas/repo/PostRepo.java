package org.fazlan.hateoas.repo;

import org.springframework.stereotype.Repository;
import org.fazlan.hateoas.web.post.Post;
import org.fazlan.hateoas.web.post.PostResource;

@Repository
public class PostRepo extends AbstractRepo<PostResource, Post> {

    @Override
    protected Post toEntity(PostResource resource) {
        return new Post(COUNTER.get(), resource.getContent());
    }
}
