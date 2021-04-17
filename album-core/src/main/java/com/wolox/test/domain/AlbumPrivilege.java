package com.wolox.test.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AlbumPrivilege {

    Long id;
    Album album;
    User user;
    PrivilegeEnum privilege;

}
