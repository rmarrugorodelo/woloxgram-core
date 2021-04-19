package com.wolox.test.domain.exception;

public class UserIsOwnerException  extends RuntimeException {

    public UserIsOwnerException(String message) {
        super(message);
    }

}
