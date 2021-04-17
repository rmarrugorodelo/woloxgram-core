package com.wolox.test.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class ErrorResponse {

    LocalDateTime timestamp;
    Integer status;
    String error;
    String message;
    String path;
    List<Object> details;

}
