package com.wolox.test.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Comment {

    Long id;
    String name;
    String email;
    String body;
    Post post;

}
