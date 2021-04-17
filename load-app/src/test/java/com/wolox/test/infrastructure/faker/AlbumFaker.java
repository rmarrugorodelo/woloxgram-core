package com.wolox.test.infrastructure.faker;

import com.wolox.test.domain.Album;
import com.wolox.test.domain.Privilege;
import com.wolox.test.domain.User;
import com.wolox.test.infrastructure.restcontroller.request.AlbumPrivilegeRequest;
import com.wolox.test.infrastructure.restcontroller.response.AlbumResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlbumFaker {

    public static final Long ID = 1L;

    public static final String READ_PRIVILEGE = "READ";

    public static final String BAD_PRIVILEGE = "WRITTES";

    public static List<Album> getALbums() {
        return List.of(
                Album.builder()
                        .title("Title 1")
                        .id(ID)
                        .user(getUser())
                        .build(),
                Album.builder()
                        .title("Title 1")
                        .id(2L)
                        .user(getUser())
                        .build()
        );
    }


    private static User getUser() {
        return User
                .builder()
                .id(ID)
                .build();
    }


    public static List<AlbumResponse> getALbumsResponse() {
        return getALbums()
                .parallelStream()
                .map(AlbumResponse::of)
                .collect(Collectors.toList());
    }



    public static AlbumPrivilegeRequest getAlbumPrivilegeRequest() {
        Set<String> privileges = new HashSet<>();
        privileges.add(READ_PRIVILEGE);
        return AlbumPrivilegeRequest
                .builder()
                .userId(ID)
                .privileges(privileges)
                .build();
    }

    public static AlbumPrivilegeRequest getBadAlbumPrivilegeRequest() {
        return AlbumPrivilegeRequest
                .builder()
                .userId(ID)
                .build();
    }

    public static Set<Privilege> getPrivileges() {
        Set<Privilege> privileges = new HashSet<>();
        privileges.add(Privilege.READ);
        privileges.add(Privilege.WRITE);
        return privileges;
    }



}
