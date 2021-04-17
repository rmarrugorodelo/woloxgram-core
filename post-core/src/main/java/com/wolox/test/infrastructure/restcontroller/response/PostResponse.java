package com.wolox.test.infrastructure.restcontroller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wolox.test.domain.Post;
import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {

    Long id;
    String title;
    String body;
    UserResponse user;

    public static PostResponse of(Post post) {

        return PostResponse
                .builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .user(
                        Objects.nonNull(post.getUser()) ? UserResponse.of(post.getUser()) : null
                )
                .build();
    }
}
