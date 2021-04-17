package com.wolox.test.infrastructure.restcontroller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wolox.test.domain.Album;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumResponse {

    Long id;

    UserResponse user;

    String title;

    public static AlbumResponse of(Album album) {
        return AlbumResponse
                .builder()
                .id(album.getId())
                .title(album.getTitle())
                .user(UserResponse.of(album.getUser()))
                .build();
    }

}