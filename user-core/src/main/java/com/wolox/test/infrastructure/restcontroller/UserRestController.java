package com.wolox.test.infrastructure.restcontroller;

import com.wolox.test.application.port.input.FindAllUsersQuery;
import com.wolox.test.infrastructure.restcontroller.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final FindAllUsersQuery findAllUsersQuery;

    public UserRestController(FindAllUsersQuery findAllUsersQuery) {
        this.findAllUsersQuery = findAllUsersQuery;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return findAllUsersQuery.execute()
                .parallelStream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }
}
