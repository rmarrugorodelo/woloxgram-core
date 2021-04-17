package com.wolox.test.infrastructure.restcontroller;


import com.wolox.test.application.port.input.FindAllAlbumsQuery;
import com.wolox.test.infrastructure.restcontroller.response.AlbumResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumRestController {

    private final FindAllAlbumsQuery findAllAlbumsQuery;

    public AlbumRestController(FindAllAlbumsQuery findAllAlbumsQuery) {
        this.findAllAlbumsQuery = findAllAlbumsQuery;
    }


    @GetMapping
    public List<AlbumResponse> findAllAlbums() {
        return findAllAlbumsQuery.execute()
                .parallelStream()
                .map(AlbumResponse::of)
                .collect(Collectors.toList());
    }

}
