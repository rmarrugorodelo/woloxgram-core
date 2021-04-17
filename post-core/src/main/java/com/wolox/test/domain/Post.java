package com.wolox.test.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Post {

    Long id;
    String title;
    String body;
    User user;

}
