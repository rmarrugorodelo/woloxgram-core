package com.wolox.test.infrastructure.restcontroller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wolox.test.domain.Privilege;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumPrivilegeRequest {

    @NotNull
    private Long userId;

    @NotEmpty
    private Set<String> privileges;

    public Set<Privilege> toPrivilegeDomain() {
        return privileges
                .parallelStream()
                .map(Privilege::fromString)
                .collect(Collectors.toSet());
    }

}
