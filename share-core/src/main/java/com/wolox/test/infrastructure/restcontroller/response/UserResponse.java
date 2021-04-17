package com.wolox.test.infrastructure.restcontroller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wolox.test.domain.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    Long id;
    String name;
    String username;
    String email;
    String phone;

    public static UserResponse of(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

}
