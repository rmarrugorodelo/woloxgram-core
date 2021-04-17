package com.wolox.test.infrastructure.rest;

import com.wolox.test.application.port.output.UserGateway;
import com.wolox.test.domain.User;
import com.wolox.test.domain.exception.RecordNotFoundException;
import com.wolox.test.infrastructure.rest.feign.UserFeignClient;
import com.wolox.test.infrastructure.rest.feign.request.IdParam;
import com.wolox.test.infrastructure.rest.feign.response.UserRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRestAdapter implements UserGateway {

    private static final String RECORD_NOT_FOUND = "No se encontró usuario con el id ingresado";

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

    @Override
    public User findById(Long id) {
        return userFeignClient.findById(IdParam
                .builder()
                .id(id).build()
        ).parallelStream()
                .findFirst()
                .map(UserRestResponse::toDomain)
                .orElseThrow(() -> new RecordNotFoundException(RECORD_NOT_FOUND));
    }

}
