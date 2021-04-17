package com.wolox.test.application;

import com.wolox.test.application.port.input.FindAlbumsByUserQuery;
import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.domain.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAlbumsByUserUseCase implements FindAlbumsByUserQuery {

    private final AlbumGateway albumGateway;

    public FindAlbumsByUserUseCase(AlbumGateway albumGateway) {
        this.albumGateway = albumGateway;
    }

    @Override
    public List<Album> execute(Long userId) {
        return albumGateway.findAll();
    }

}
