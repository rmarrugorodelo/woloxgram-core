package com.wolox.test.infrastructure.rest.feign.response;


import com.wolox.test.domain.Post;
import com.wolox.test.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRestResponse {

    Long id;
    String title;
    String body;
    Long userId;

    public Post toDomain() {
        return Post
                .builder()
                .id(id)
                .title(title)
                .body(body)
                .user(User
                        .builder()
                        .id(userId)
                        .build()
                )
                .build();
    }
}
