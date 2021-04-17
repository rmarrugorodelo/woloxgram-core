package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.CommentGateway;
import com.wolox.test.domain.Comment;
import com.wolox.test.infrastructure.rest.feign.CommentFeignClient;
import com.wolox.test.infrastructure.rest.feign.request.NameParam;
import com.wolox.test.infrastructure.rest.feign.response.CommentRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentRestAdapter implements CommentGateway {

    private final CommentFeignClient commentFeignClient;

    public CommentRestAdapter(CommentFeignClient commentFeignClient) {
        this.commentFeignClient = commentFeignClient;
    }

    @Override
    public List<Comment> findAll() {
        return commentFeignClient.findAll()
                .parallelStream()
                .map(CommentRestResponse::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByName(String name) {
        return commentFeignClient.findByName(NameParam
                .builder()
                .name(name)
                .build()
        ).parallelStream()
                .map(CommentRestResponse::toDomain)
                .collect(Collectors.toList());
    }


}
