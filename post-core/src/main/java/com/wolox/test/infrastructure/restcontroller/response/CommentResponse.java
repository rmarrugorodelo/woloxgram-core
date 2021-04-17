package com.wolox.test.infrastructure.restcontroller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wolox.test.domain.Comment;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {

    Long id;
    String name;
    String email;
    String body;
    PostResponse post;

    public static CommentResponse of(Comment comment) {
        return CommentResponse
                .builder()
                .id(comment.getId())
                .name(comment.getName())
                .body(comment.getBody())
                .post(PostResponse.of(comment.getPost()))
                .build();
    }
}
