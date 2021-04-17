package com.wolox.test.infrastructure.rest.feign2.response;

import com.wolox.test.domain.User;
import lombok.Data;

@Data
public class UserRestResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;

    public User toDomain() {
        return User
                .builder()
                .id(id)
                .name(name)
                .username(username)
                .email(email)
                .phone(phone)
                .build();
    }

}
