package com.wolox.test.infrastructure.rest.feign.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IdParam {

    Long id;

}
