package com.wolox.test.infrastructure.faker;

import com.wolox.test.domain.User;
import com.wolox.test.infrastructure.restcontroller.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserFaker {

    public static final Long ID = 1L;
    public static final Long ID2 = 2L;
    private static final String NAME = "Richard Marrugo";

    public static List<User> getUsers() {
        return List.of(
                User.builder()
                        .id(ID)
                        .name(NAME)
                        .build(),
                User.builder()
                        .id(ID2)
                        .name(NAME)
                        .build()
        );
    }

    public static List<UserResponse> getUsersResponse() {
        return getUsers()
                .parallelStream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

}
