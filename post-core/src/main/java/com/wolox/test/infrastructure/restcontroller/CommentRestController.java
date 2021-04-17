package com.wolox.test.infrastructure.restcontroller;


import com.wolox.test.application.port.input.FindAllCommentsQuery;
import com.wolox.test.application.port.input.FindCommentsByNameQuery;
import com.wolox.test.application.port.input.FindCommentsByUserQuery;
import com.wolox.test.infrastructure.restcontroller.response.CommentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentRestController {

    private final FindAllCommentsQuery findAllCommentsQuery;

    private final FindCommentsByNameQuery findCommentsByNameQuery;

    private final FindCommentsByUserQuery findCommentsByUserQuery;

    public CommentRestController(FindAllCommentsQuery findAllCommentsQuery,
                                 FindCommentsByNameQuery findCommentsByNameQuery,
                                 FindCommentsByUserQuery findCommentsByUserQuery) {
        this.findAllCommentsQuery = findAllCommentsQuery;
        this.findCommentsByNameQuery = findCommentsByNameQuery;
        this.findCommentsByUserQuery = findCommentsByUserQuery;
    }

    @GetMapping
    public List<CommentResponse> findAll() {
        return findAllCommentsQuery.execute()
                .parallelStream()
                .map(CommentResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "name")
    public List<CommentResponse> findByName(@RequestParam("name") String name) {
        return findCommentsByNameQuery.execute(name)
                .parallelStream()
                .map(CommentResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "userId")
    public List<CommentResponse> findByName(@RequestParam("userId") Long userId) {
        return findCommentsByUserQuery.execute(userId)
                .parallelStream()
                .map(CommentResponse::of)
                .collect(Collectors.toList());
    }
}
