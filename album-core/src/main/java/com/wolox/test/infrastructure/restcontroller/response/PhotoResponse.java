package com.wolox.test.infrastructure.restcontroller.response;

import com.wolox.test.domain.Photo;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PhotoResponse {

    Long id;
    Long albumId;
    String title;
    String url;
    String thumbnailUrl;

    public static PhotoResponse of(Photo photo) {
        return PhotoResponse.builder()
                .id(photo.getId())
                .albumId(photo.getAlbum().getId())
                .title(photo.getTitle())
                .url(photo.getUrl())
                .thumbnailUrl(photo.getThumbnailUrl())
                .build();
    }

}
