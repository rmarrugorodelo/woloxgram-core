package com.wolox.test.infrastructure.rest.feign.response;


import com.wolox.test.domain.Comment;
import com.wolox.test.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRestResponse {

    Long id;
    String name;
    String email;
    String body;
    Long postId;

    public Comment toDomain() {
        return Comment
                .builder()
                .id(id)
                .name(name)
                .email(email)
                .body(body)
                .post(Post
                        .builder()
                        .id(postId)
                        .build()
                )
                .build();
    }
}
