package com.wolox.test.infrastructure.restcontroller;

import com.wolox.test.application.port.input.FindAlbumsByUserQuery;
import com.wolox.test.application.port.input.FindPhotosByUserQuery;
import com.wolox.test.infrastructure.restcontroller.response.AlbumResponse;
import com.wolox.test.infrastructure.restcontroller.response.PhotoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final FindPhotosByUserQuery findPhotosByUserQuery;

    private final FindAlbumsByUserQuery findAlbumsByUserQuery;

    public UserController(FindPhotosByUserQuery findPhotosByUserQuery,
                          FindAlbumsByUserQuery findAlbumsByUserQuery) {
        this.findPhotosByUserQuery = findPhotosByUserQuery;
        this.findAlbumsByUserQuery = findAlbumsByUserQuery;
    }

    @GetMapping("/{id}/photos")
    public List<PhotoResponse> findPhotos(@PathVariable Long id) {
        return findPhotosByUserQuery.execute(id)
                .parallelStream()
                .map(PhotoResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/albums")
    public List<AlbumResponse> findAlbums(@PathVariable Long id) {
        return findAlbumsByUserQuery.execute(id)
                .parallelStream()
                .map(AlbumResponse::of)
                .collect(Collectors.toList());
    }

}
