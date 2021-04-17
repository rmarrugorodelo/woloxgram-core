package com.wolox.test.domain;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Photo {

    Long id;
    Album album;
    String title;
    String url;
    String thumbnailUrl;

}
