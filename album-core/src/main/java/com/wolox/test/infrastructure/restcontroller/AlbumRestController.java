package com.wolox.test.infrastructure.restcontroller;


import com.wolox.test.application.port.input.AssignPrivilegeForUserCommand;
import com.wolox.test.application.port.input.FindAllAlbumsQuery;
import com.wolox.test.application.port.input.FindUsersByAlbumAndPrivilegeQuery;
import com.wolox.test.domain.Privilege;
import com.wolox.test.infrastructure.restcontroller.request.AlbumPrivilegeRequest;
import com.wolox.test.infrastructure.restcontroller.response.AlbumResponse;
import com.wolox.test.infrastructure.restcontroller.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumRestController {

    private final FindAllAlbumsQuery findAllAlbumsQuery;

    private final AssignPrivilegeForUserCommand assignPrivilegeForUserCommand;

    private final FindUsersByAlbumAndPrivilegeQuery findUsersByAlbumAndPrivilegeQuery;

    public AlbumRestController(FindAllAlbumsQuery findAllAlbumsQuery,
                               AssignPrivilegeForUserCommand assignPrivilegeForUserCommand,
                               FindUsersByAlbumAndPrivilegeQuery findUsersByAlbumAndPrivilegeQuery) {
        this.findAllAlbumsQuery = findAllAlbumsQuery;
        this.assignPrivilegeForUserCommand = assignPrivilegeForUserCommand;
        this.findUsersByAlbumAndPrivilegeQuery = findUsersByAlbumAndPrivilegeQuery;
    }


    @GetMapping
    public List<AlbumResponse> findAllAlbums() {
        return findAllAlbumsQuery.execute()
                .parallelStream()
                .map(AlbumResponse::of)
                .collect(Collectors.toList());
    }

    @PostMapping("{id}/assign-privilege-user")
    @ResponseStatus(HttpStatus.OK)
    public void assignPrivilegeForUser(@PathVariable Long id,
                                       @Valid @RequestBody AlbumPrivilegeRequest request) {
        assignPrivilegeForUserCommand.execute(request.getUserId(),
                id, request.toPrivilegeDomain());
    }

    @GetMapping("{id}/users")
    public List<UserResponse> findUsersByPrivilege(@PathVariable Long id, @RequestParam String privilege) {
        return findUsersByAlbumAndPrivilegeQuery.execute(id, Privilege.fromString(privilege))
                .parallelStream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

}
