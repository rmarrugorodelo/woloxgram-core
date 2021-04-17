package com.wolox.test.infrastructure.rest.feign.response;

import com.wolox.test.domain.Album;
import com.wolox.test.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlbumRestResponse {

    private Long id;

    private Long userId;

    private String title;

    public Album toDomain() {
        return Album
                .builder()
                .id(id)
                .title(title)
                .user(User
                        .builder()
                        .id(userId)
                        .build())
                .build();
    }
}