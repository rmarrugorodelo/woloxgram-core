package com.wolox.test.domain;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Album {

    Long id;
    String title;
    User user;

}
