package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.UserGateway;
import com.wolox.test.domain.User;
import com.wolox.test.infrastructure.rest.feign.UserFeignClient;
import com.wolox.test.infrastructure.rest.feign.response.UserRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRestAdapter implements UserGateway {

    private final UserFeignClient userFeignClient;

    public UserRestAdapter(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    @Override
    public List<User> findAll() {
        return userFeignClient.findAll()
                .parallelStream()
                .map(UserRestResponse::toDomain)
                .collect(Collectors.toList());
    }

}
