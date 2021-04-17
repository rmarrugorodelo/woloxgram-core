package com.wolox.test.application;

import com.wolox.test.application.port.input.FindPhotosByUserQuery;
import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.application.port.output.PhotoGateway;
import com.wolox.test.domain.Album;
import com.wolox.test.domain.Photo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAPhotosByUserUseCase implements FindPhotosByUserQuery {

    private final AlbumGateway albumGateway;

    private final PhotoGateway photoGateway;

    public FindAPhotosByUserUseCase(AlbumGateway albumGateway,
                                    PhotoGateway photoGateway) {
        this.albumGateway = albumGateway;
        this.photoGateway = photoGateway;
    }

    @Override
    public List<Photo> execute(Long userId) {
        List<Album> albums = albumGateway.findByUserId(userId);
        return photoGateway.findAll()
                .parallelStream()
                .filter(photo -> albums.stream()
                        .anyMatch(album -> album.getId().equals(photo.getAlbum().getId())))
                .collect(Collectors.toList());
    }

}
