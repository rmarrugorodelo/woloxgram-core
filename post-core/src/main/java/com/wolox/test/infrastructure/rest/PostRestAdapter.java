package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.PostGateway;
import com.wolox.test.domain.Post;
import com.wolox.test.infrastructure.rest.feign.PostFeignClient;
import com.wolox.test.infrastructure.rest.feign.request.UserIdParam;
import com.wolox.test.infrastructure.rest.feign.response.PostRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostRestAdapter implements PostGateway {

    private final PostFeignClient postFeignClient;

    public PostRestAdapter(PostFeignClient postFeignClient) {
        this.postFeignClient = postFeignClient;
    }

    @Override
    public List<Post> findAll() {
        return postFeignClient.findAll()
                .parallelStream()
                .map(PostRestResponse::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return postFeignClient.findByUser(UserIdParam
                .builder()
                .userId(userId)
                .build()
        ).parallelStream()
                .map(PostRestResponse::toDomain)
                .collect(Collectors.toList());
    }

}
