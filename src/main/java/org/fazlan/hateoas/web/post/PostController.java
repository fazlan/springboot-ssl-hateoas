package org.fazlan.hateoas.web.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.fazlan.hateoas.repo.CommentRepo;
import org.fazlan.hateoas.repo.PostRepo;
import org.fazlan.hateoas.web.comment.CommentResource;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepo repo;

    @Autowired
    private CommentRepo commentRepo;

    @RequestMapping(method = POST)
    @ResponseBody
    public Link createPost(@RequestBody PostResource post) {
        return getPost(repo.create(post)).getId();
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public PostResource getPost(@PathVariable("id") Integer id) {
        return new PostResource(repo.get(id));
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public List<Link> listPosts() {
        return repo.list().stream()
                .map(PostResource::new)
                .map(PostResource::getId)
                .collect(toList());
    }

    @RequestMapping(value = "/{id}/comments", method = POST)
    @ResponseBody
    public PostResource addComment(@PathVariable("id") Integer postId, @RequestBody CommentResource comment) {
        repo.get(postId).addComment(commentRepo.get(commentRepo.create(comment)));
        return getPost(postId);
    }

    @RequestMapping(value = "/{id}/comments", method = GET)
    @ResponseBody
    public List<Link> getComments(@PathVariable("id") Integer postId) {
        return repo.get(postId).getComments()
                .stream()
                .map(CommentResource::new)
                .map(cr -> cr.withRel("comment").getLink("comment"))
                .collect(toList());
    }
}
