package com.wolox.test.application.port.input;

import com.wolox.test.domain.PrivilegeEnum;

import java.util.Set;

public interface CreatePrivilegeForUserCommand {

    void execute(Long userId, Long albumId, Set<PrivilegeEnum> privileges);

}
