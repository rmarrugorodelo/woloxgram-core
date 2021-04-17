package com.wolox.test.application.port.input;

import com.wolox.test.domain.Privilege;
import com.wolox.test.domain.User;

import java.util.List;

public interface FindUsersByAlbumAndPrivilegeQuery {

    List<User> execute(Long albumId, Privilege privilege);

}
