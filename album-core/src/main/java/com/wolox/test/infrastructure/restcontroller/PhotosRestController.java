package com.wolox.test.infrastructure.restcontroller;


import com.wolox.test.application.port.input.FindAllPhotosQuery;
import com.wolox.test.infrastructure.restcontroller.response.PhotoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotosRestController {

    private final FindAllPhotosQuery findAllPhotosQuery;

    public PhotosRestController(FindAllPhotosQuery findAllPhotosQuery) {
        this.findAllPhotosQuery = findAllPhotosQuery;
    }

    @GetMapping
    public List<PhotoResponse> findAllPhotos() {
        return findAllPhotosQuery.execute()
                .parallelStream()
                .map(PhotoResponse::of)
                .collect(Collectors.toList());
    }

}
