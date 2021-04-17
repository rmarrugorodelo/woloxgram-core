package com.wolox.test.application.port.input;

import com.wolox.test.domain.Privilege;

import java.util.Set;

public interface AssignPrivilegeForUserCommand {

    void execute(Long userId, Long albumId, Set<Privilege> privileges);
}
