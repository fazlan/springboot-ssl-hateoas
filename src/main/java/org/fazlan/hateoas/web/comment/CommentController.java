package org.fazlan.hateoas.web.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.fazlan.hateoas.repo.CommentRepo;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepo repo;

    @RequestMapping(method = GET)
    @ResponseBody
    public List<Link> listComments() {
        return repo.list().stream()
                .map(CommentResource::new)
                .map(CommentResource::withSefRef)
                .map(CommentResource::getId)
                .collect(toList());
    }

    @RequestMapping(value = "/{id}", method = GET)
    @ResponseBody
    public CommentResource getComment(@PathVariable("id") Integer id) {
        return new CommentResource(repo.get(id)).withSefRef();
    }
}
