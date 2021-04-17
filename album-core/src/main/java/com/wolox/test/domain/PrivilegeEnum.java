package com.wolox.test.domain;

import com.wolox.test.domain.exception.BadRequestException;

import java.util.Arrays;

public enum PrivilegeEnum {

    READ("READ"), WRITE("WRITE");

    private final String value;

    PrivilegeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PrivilegeEnum fromString(String value) {
        return Arrays.stream(PrivilegeEnum.values())
                .filter(providerType -> (providerType.getValue()).equals(value))
                .findFirst().orElseThrow(() -> new BadRequestException(String.format("Permiso ingresado no es valido [%s]", value)));
    }
}
