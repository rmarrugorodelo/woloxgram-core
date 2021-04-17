package com.wolox.test.domain;

import com.wolox.test.domain.exception.BadRequestException;

import java.util.Arrays;

public enum Privilege {

    READ("READ"), WRITE("WRITE");

    private final String value;

    Privilege(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Privilege fromString(String value) {
        return Arrays.stream(Privilege.values())
                .filter(providerType -> (providerType.getValue()).equals(value))
                .findFirst().orElseThrow(() -> new BadRequestException(String.format("Permiso ingresado no es valido [%s]", value)));
    }
}
