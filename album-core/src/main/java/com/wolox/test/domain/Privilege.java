package com.wolox.test.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Privilege {

    Long id;
    PrivilegeEnum description;

}
