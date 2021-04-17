package com.wolox.test.application.port.input;

import com.wolox.test.domain.User;

import java.util.List;

public interface FindAllUsersQuery {

    List<User> execute();

}
