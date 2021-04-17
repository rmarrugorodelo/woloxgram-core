package com.wolox.test.infrastructure.restcontroller;


import com.wolox.test.application.port.input.FindAllPostsQuery;
import com.wolox.test.infrastructure.restcontroller.response.PostResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {

    private final FindAllPostsQuery findAllPostsQuery;

    public PostRestController(FindAllPostsQuery findAllPostsQuery) {
        this.findAllPostsQuery = findAllPostsQuery;
    }

    @GetMapping
    public List<PostResponse> findAll() {
        return findAllPostsQuery.execute()
                .parallelStream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }
}
