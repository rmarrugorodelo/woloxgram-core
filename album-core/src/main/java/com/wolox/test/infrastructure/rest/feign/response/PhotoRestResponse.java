package com.wolox.test.infrastructure.rest.feign.response;

import com.wolox.test.domain.Album;
import com.wolox.test.domain.Photo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoRestResponse {

    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo toDomain() {
        return Photo
                .builder()
                .id(id)
                .album(Album
                        .builder()
                        .id(albumId)
                        .build())
                .title(title)
                .url(url)
                .thumbnailUrl(thumbnailUrl)
                .build();
    }
}
