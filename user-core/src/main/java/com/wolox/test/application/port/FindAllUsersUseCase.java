package com.wolox.test.application.port;

import com.wolox.test.application.port.input.FindAllUsersQuery;
import com.wolox.test.application.port.output.UserGateway;
import com.wolox.test.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsersUseCase implements FindAllUsersQuery {

    private final UserGateway userGateway;

    public FindAllUsersUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<User> execute() {
        return userGateway.findAll();
    }

}
