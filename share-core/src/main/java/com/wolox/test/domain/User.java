package com.wolox.test.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    Long id;
    String name;
    String username;
    String email;
    String phone;

}
