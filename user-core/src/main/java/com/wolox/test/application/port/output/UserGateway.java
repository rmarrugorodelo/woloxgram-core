package com.wolox.test.application.port.output;

import com.wolox.test.domain.User;

import java.util.List;

public interface UserGateway {

    List<User> findAll();

}
