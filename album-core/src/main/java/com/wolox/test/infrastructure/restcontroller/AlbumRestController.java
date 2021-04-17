package com.wolox.test.infrastructure.restcontroller;


import com.wolox.test.application.port.input.CreatePrivilegeForUserCommand;
import com.wolox.test.application.port.input.FindAllAlbumsQuery;
import com.wolox.test.infrastructure.restcontroller.request.AlbumPrivilegeRequest;
import com.wolox.test.infrastructure.restcontroller.response.AlbumResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumRestController {

    private final FindAllAlbumsQuery findAllAlbumsQuery;

    private final CreatePrivilegeForUserCommand createPrivilegeForUserCommand;

    public AlbumRestController(FindAllAlbumsQuery findAllAlbumsQuery,
                               CreatePrivilegeForUserCommand createPrivilegeForUserCommand) {
        this.findAllAlbumsQuery = findAllAlbumsQuery;
        this.createPrivilegeForUserCommand = createPrivilegeForUserCommand;
    }


    @GetMapping
    public List<AlbumResponse> findAllAlbums() {
        return findAllAlbumsQuery.execute()
                .parallelStream()
                .map(AlbumResponse::of)
                .collect(Collectors.toList());
    }

    @PostMapping("{id}/create-privilege-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPrivilegeForUser(@PathVariable Long id,
                                       @Valid @RequestBody AlbumPrivilegeRequest request) {
        createPrivilegeForUserCommand.execute(request.getUserId(),
                id, request.toPrivilegeDomain());
    }

}
