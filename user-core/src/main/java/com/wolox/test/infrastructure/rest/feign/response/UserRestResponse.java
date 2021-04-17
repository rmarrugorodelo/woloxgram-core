package com.wolox.test.infrastructure.rest.feign.response;

import com.wolox.test.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
                .email(email)
                .phone(phone)
                .build();
    }

}
